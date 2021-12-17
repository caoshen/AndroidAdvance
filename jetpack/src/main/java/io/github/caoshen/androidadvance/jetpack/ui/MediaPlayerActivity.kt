package io.github.caoshen.androidadvance.jetpack.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.Handler
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.os.Vibrator
import android.telephony.TelephonyManager
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import io.github.caoshen.androidadvance.jetpack.mvvm.base.BaseActivity
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.HashMap

class MediaPlayerActivity: BaseActivity() {
//    private val TAG = "FindMyPhoneActivity"
//
//    private var isFlashOn = false
//    private val uiHandler: Handler? = Handler()
//    private val blink: Runnable = object : Runnable {
//        override fun run() {
//            if (isFlashOn) {
//                turnOffFlash()
//            } else {
//                turnOnFlash()
//            }
////            Log.d(
////                TAG,
////                SettingsUtil.enquiry(applicationContext, MOBILECONTROL_FLASHLIGHT).toString() + ""
////            )
//            uiHandler!!.postDelayed(this, 2000)
//        }
//    }
//
//    private val vibrate: Runnable = object : Runnable {
//        override fun run() {
//            vibrator = VibrateUtil.(this)(applicationContext, 2000)
//            uiHandler!!.postDelayed(this, 4000)
//        }
//    }
//
//    private val playMusic: Runnable = Runnable {
//        InnerMediaPlayer.Companion.default.soundID = 0
//        InnerMediaPlayer.Companion.default.startSingleTone(baseContext)
//        Log.d(TAG, "current volume: " + InnerMediaPlayer.Companion.default.volume / 15.0f)
//        //            uiHandler.postDelayed(this, 13000);
//    }
//
//    private val elevateVolume: Runnable = object : Runnable {
//        override fun run() {
//            if (InnerMediaPlayer.Companion.default.mCurrentState == InnerMediaPlayer.STATE_PLAYING) {
//                InnerMediaPlayer.Companion.default.volume++
//                InnerMediaPlayer.Companion.default.mMediaPlayer.setVolume(
//                    InnerMediaPlayer.Companion.default.volume / 15.0f,
//                    InnerMediaPlayer.Companion.default.volume / 15.0f
//                )
//            }
//            uiHandler!!.postDelayed(this, 1000)
//        }
//    }
//
//    private val waitFinishLockState: Runnable = Runnable { finish() }
//
//    private var screenWakeLock: WakeLock? = null
//    private var vibrator: Vibrator? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val powerManager = this.getSystemService(POWER_SERVICE) as PowerManager
//        if (!powerManager.isScreenOn) {
//            screenWakeLock = powerManager.newWakeLock(
//                PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_DIM_WAKE_LOCK,
//                "xiaoai:find phone"
//            )
//            screenWakeLock.acquire(0)
//        }
//        val localWindow = window
//        localWindow.addFlags(
//            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//        Log.d(TAG, uiHandler.toString() + ">>>1" + (uiHandler != null))
//        setContentView(R.layout.activity_find_my_phone)
//        val content: View = findViewById(R.id.content_view)
//        content.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
//        isFlashOn = false
//        val localLayoutParams = localWindow.attributes
//        val isNotch = SystemProperties.getInt("ro.miui.notch", 0) === 1
//        Log.d(TAG, "isNotch=$isNotch")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            localLayoutParams.layoutInDisplayCutoutMode =
//                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//        }
//        localLayoutParams.screenBrightness = 1.0f
//        localWindow.attributes = localLayoutParams
//        InnerMediaPlayer.Companion.default.load(baseContext)
//        Log.d(TAG, uiHandler.toString() + ">>>2" + (uiHandler != null))
//        uiHandler!!.postDelayed(blink, 0)
//        uiHandler.postDelayed(vibrate, 0)
//        uiHandler.postDelayed(playMusic, 0)
//        uiHandler.postDelayed(elevateVolume, 0)
//        uiHandler.postDelayed({ finish() }, 90000)
//        Log.d(TAG, uiHandler.toString() + ">>>3" + (uiHandler != null))
//        requestAudioFocus()
//        val filter = IntentFilter()
//        filter.addAction(Intent.ACTION_SCREEN_OFF)
//        filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
//        registerReceiver(mBatInfoReceiver, filter)
//        StatusBarUtil.collapseStatusBar(this)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        uiHandler!!.removeCallbacks(waitFinishLockState)
//        Log.d(TAG, uiHandler.toString() + ">>>4" + (uiHandler != null))
//    }
//
//    override fun onPause() {
//        super.onPause()
//        //锁屏下的特殊处理
//        if (KeyguardUtils.isLockState()) {
//            uiHandler!!.postDelayed(waitFinishLockState, 1000)
//        } else {
//            uiHandler!!.postDelayed(waitFinishLockState, 0)
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//    }
//
//    override fun onDestroy() {
//        destroyAllAction()
//        unregisterReceiver(mBatInfoReceiver)
//        super.onDestroy()
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        finish()
//        return super.onTouchEvent(event)
//    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        Log.d(TAG, "onKeyDown")
//        finish()
//        return super.onKeyDown(keyCode, event)
//    }
//
//    private fun turnOnFlash() {
//        if (!isFlashOn) {
//            SettingsUtil.change(applicationContext, MOBILECONTROL_FLASHLIGHT, 1)
//            isFlashOn = true
//            Log.d(TAG, "flashlight is on")
//        }
//    }
//
//    private fun turnOffFlash() {
//        if (isFlashOn) {
//            SettingsUtil.change(applicationContext, MOBILECONTROL_FLASHLIGHT, 0)
//            isFlashOn = false
//            Log.d(TAG, "flashlight is off")
//        }
//    }
//
//    fun requestAudioFocus(): Int {
//        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
//        val status: Int
//        status = audioManager.requestAudioFocus(
//            mAudioFocusListener,
//            AudioManager.STREAM_MUSIC,
//            AudioManager.AUDIOFOCUS_GAIN
//        )
//        return status
//    }
//
//    fun abandonAudioFocus(): Int {
//        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
//        return audioManager.abandonAudioFocus(mAudioFocusListener)
//    }
//
//    private val mAudioFocusListener: OnAudioFocusChangeListener =
//        OnAudioFocusChangeListener { focusChange ->
//            Log.i(TAG, "onAudioFocusChange focusChange:$focusChange")
//            when (focusChange) {
//                AudioManager.AUDIOFOCUS_GAIN, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK -> {
//                    InnerMediaPlayer.Companion.default.mediaPlayerPlaySound()
//                }
//                AudioManager.AUDIOFOCUS_LOSS, AudioManager.AUDIOFOCUS_LOSS_TRANSIENT, AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
//                    InnerMediaPlayer.Companion.default.pause()
//                }
//                else -> {
//                }
//            }
//        }
//
//    private fun destroyAllAction() {
//        uiHandler!!.removeCallbacksAndMessages(null)
//        if (screenWakeLock != null && screenWakeLock!!.isHeld) {
//            try {
//                screenWakeLock!!.release()
//            } catch (t: Throwable) {
//                Log.d(TAG, t.toString())
//            }
//        }
//        abandonAudioFocus()
//        if (vibrator != null) {
//            vibrator!!.cancel()
//        }
//        SettingsUtil.change(applicationContext, MOBILECONTROL_FLASHLIGHT, 0)
//        //关闭音乐
//        InnerMediaPlayer.Companion.default.release()
//        InnerMediaPlayer.Companion.default.unload()
//        InnerMediaPlayer.Companion.default.soundID = 0
//        InnerMediaPlayer.Companion.default.volume = 3.0f
//    }
//
//    internal class InnerMediaPlayer private constructor() {
////        private var mLoaded = false
////        private var mMediaPlayer: MediaPlayer? = null
////        private val mSoundIDHashMap: HashMap<Int?, Int?> = HashMap<Any?, Any?>()
////        private val mSoundPoolLock = Any()
////        private val maxVolume = 15.0f
////        var volume = 3.0f
////        var soundID = 0
////        private var mCurrentState = STATE_IDLE
////
////        private object SoundPoolHelperHolder {
////            val default = InnerMediaPlayer()
////                get() = SoundPoolHelperHolder.field
////        }
////
////        fun load(context: Context?) {
////            mLoaded = false
////            mCurrentState = STATE_IDLE
////            synchronized(mSoundPoolLock) {
////                mSoundIDHashMap.put(
////                    FIND_PHONE0,
////                    SOUND_RESOURCE_IDS.get(0)
////                )
////                mLoaded = true
////                Log.d(TAG, "LOAD")
////            }
////        }
////
////        private fun mediaPlayerPrepare(context: Context, streamtype: Int, resid: Int) {
////            try {
////                mCurrentState = STATE_PREPARING
////                if (mMediaPlayer != null) {
////                    mMediaPlayer!!.stop()
////                    mMediaPlayer!!.release()
////                    mMediaPlayer = null
////                }
////                mMediaPlayer = MediaPlayer()
////                AudioMediaUtils.setMediaPlayerStreamType(mMediaPlayer, streamtype)
////                Log.d(TAG, java.lang.Float.toString(volume / maxVolume))
////                val afd = context.resources.openRawResourceFd(resid)
////                mMediaPlayer!!.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
////                afd.close()
////                mMediaPlayer!!.prepare()
////                mMediaPlayer!!.setOnCompletionListener(mCompletionListener)
////                mMediaPlayer!!.setOnErrorListener(mOnErrorListener)
////                mCurrentState = STATE_PREPARED
////                Log.d(TAG, "BGM PREPARING")
////            } catch (ex: IOException) {
////                Log.e(TAG, "", ex)
////                mCurrentState = STATE_ERROR
////                mMediaPlayer = null
////            } catch (ex: IllegalArgumentException) {
////                Log.e(TAG, "", ex)
////                mCurrentState = STATE_ERROR
////                mMediaPlayer = null
////            }
////        }
////
////        private fun mediaPlayerPrepare(context: Context, resid: Int) {
////            mediaPlayerPrepare(context, AudioManager.STREAM_ALARM, resid)
////        }
////
////        private val mCompletionListener: OnCompletionListener = OnCompletionListener {
////            Log.e(TAG, "onCompletion")
////            mCurrentState = STATE_PLAYBACK_COMPLETED
////            if (mMediaPlayer != null) {
////                // 循环播放
////                mMediaPlayer!!.start()
////                mMediaPlayer!!.isLooping = true
////            }
////        }
////        private val mOnErrorListener: MediaPlayer.OnErrorListener =
////            MediaPlayer.OnErrorListener { mp, what, extra ->
////                Log.e(TAG, "onError")
////                mCurrentState = STATE_ERROR
////                if (mMediaPlayer != null) {
////                    mMediaPlayer!!.stop()
////                    mMediaPlayer!!.release()
////                    mMediaPlayer = null
////                }
////                false
////            }
////
////        private fun mediaPlayerPlaySound() {
////            try {
////                mMediaPlayer!!.start()
////                mCurrentState = STATE_PLAYING
////            } catch (e: Exception) {
////                mMediaPlayer = null
////                Log.e(TAG, "", e)
////            }
////        }
////
////        fun pause() {
////            if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
////                Log.d(TAG, "player: pause play")
////                mMediaPlayer!!.pause()
////                mCurrentState = STATE_PAUSED
////            }
////        }
////
////        val isPlaying: Boolean
////            get() = !((mCurrentState == STATE_ERROR
////                    ) || (mCurrentState == STATE_IDLE
////                    ) || (mCurrentState == STATE_PAUSED
////                    ) || (mCurrentState == STATE_PLAYBACK_COMPLETED))
////
////        fun release() {
////            if (mMediaPlayer != null) {
////                Log.d(TAG, "mMediaPlayer release")
////                mMediaPlayer!!.stop()
////                mMediaPlayer!!.release()
////                mMediaPlayer = null
////            }
////            mCurrentState = STATE_IDLE
////        }
////
////        fun unload() {
////            synchronized(mSoundPoolLock) { release() }
////            mLoaded = false
////        }
////
////        fun startSingleTone(context: Context) {
////            ThreadPoolManager.executeOnFixedThreadPool(Runnable {
////                Log.d(TAG, "startSingleTone")
////                startSingleToneInner(context)
////            })
////        }
////
////        private fun startSingleToneInner(context: Context) {
////            Log.d(TAG, "startSingleToneInner   $mLoaded")
////            if (!mLoaded) {
////                return
////            }
////            Log.i(TAG, "play: $soundID")
////            mediaPlayerPrepare(context, (mSoundIDHashMap[soundID])!!)
////            mediaPlayerPlaySound()
////            DebugUtil.AutoTest.saveVoiceTriggerFileForAutoTest("startToneInner: $soundID")
////        }
////
////        companion object {
////            private val TAG = "InnerMediaPlayer"
////
////            // all possible internal states
////            private val STATE_ERROR = -1
////            private val STATE_IDLE = 0
////            private val STATE_PREPARING = 1
////            private val STATE_PREPARED = 2
////            val STATE_PLAYING = 3
////            private val STATE_PAUSED = 4
////            private val STATE_PLAYBACK_COMPLETED = 5
////            val FIND_PHONE0 = 0X00
////            private val SOUND_RESOURCE_IDS = intArrayOf(
////                R.raw.lottie_v1
////            )
////        }
//    }
//
//    private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent) {
////            final String action = intent.getAction();
////
////            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
////                finish();
////                Log.d(TAG, "电源键监听");
////            } else if (TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(action) ) {
////                Log.d(TAG, "ACTION_PHONE_STATE_CHANGED");
////                TelephonyManager mTelephonyManager =(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
////                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
////                int state = mTelephonyManager.getCallState();
////                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
////                if(current>0){
////                    FindMyPhoneActivity.this.finish();
////                    return;
////                }
////                switch(state){
////                    case TelephonyManager.CALL_STATE_RINGING:
////                        Log.d(TAG, "CALL_STATE_RINGING");
////                        FindMyPhoneActivity.this.finish();
////                        //做来电要执行的操作
////                        break;
////                    case TelephonyManager.CALL_STATE_IDLE:
////                        Log.d(TAG, "CALL_STATE_IDLE");
////                        break;
////                    case TelephonyManager.CALL_STATE_OFFHOOK:
////                        Log.d(TAG, "CALL_STATE_OFFHOOK");
////                        FindMyPhoneActivity.this.finish();
////                        break;
////                }
////            }
//        }
//    }

}