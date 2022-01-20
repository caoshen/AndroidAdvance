package io.github.caoshen.androidadvance.jetpack.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.lifecycle.viewModelScope
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.baselib.base.BaseActivity
import kotlinx.coroutines.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.lang.ref.WeakReference


class RecordActivity : BaseActivity(R.layout.activity_record) {
    private var mClientMessenger: Messenger? = null
    private var mClientHandler: Handler? = null

    private var mServiceMessenger: Messenger? = null

    companion object {
        const val CMSG_STOP_HOTWORD_RECORDING = 1
        const val CMSG_GET_HOTWORD_FD = 4
        const val CMSG_GET_HOTWORD_FD_REPLY = 5
        const val TAG = "RecordActivity"
    }

    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected:$name")
            if (service == null) {
                return
            }

            mServiceMessenger = Messenger(service)
            val fdMsg = Message.obtain(null, CMSG_GET_HOTWORD_FD)
            fdMsg.replyTo = mClientMessenger

            mServiceMessenger!!.send(fdMsg)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected:$name")
        }
    }

    override fun init() {
        mClientHandler = ClientHandler(this)
        mClientMessenger = Messenger(mClientHandler)

        val intent = Intent()
        intent.setClassName("com.miui.voicetrigger", "com.miui.voicetrigger.VoiceAuthService")
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }

    class ClientHandler(val activity: RecordActivity) : Handler(Looper.getMainLooper()) {
        private var weakReference: WeakReference<RecordActivity> = WeakReference<RecordActivity>(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val activity = weakReference.get() ?: return

            when (msg.what) {
                CMSG_GET_HOTWORD_FD_REPLY -> {
                    if (msg.obj is ParcelFileDescriptor) {
                        val pfd: ParcelFileDescriptor = msg.obj as ParcelFileDescriptor
                        Log.d(TAG, "get file descriptor from VoiceAuthService, $pfd")

                        activity.writeRecordFile(pfd)
                    } else {
                        Log.d(TAG, "get file descriptor failed. $msg")
                    }
                }
            }
        }

    }

    private fun writeRecordFile(pfd: ParcelFileDescriptor) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "start write record file")
            try {
                val hotWordRecordFilePath = "${filesDir}${File.separator}hotword_record.pcm"
                val fos = FileOutputStream(hotWordRecordFilePath)
                val fileInputStream = FileInputStream(pfd.fileDescriptor)
                var bytesRead: Int
                val buffer = ByteArray(4096)
                while ((fileInputStream.read(buffer).also {
                        bytesRead = it
                    }) > 0) {
                    fos.write(buffer, 0, bytesRead)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e(TAG, "exception:$e")
            }
        }
    }

    fun stopRecording() {
        mClientHandler!!.sendEmptyMessage(CMSG_STOP_HOTWORD_RECORDING)
    }
}