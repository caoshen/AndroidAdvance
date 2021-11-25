package io.github.caoshen.androidadvance.jetpack.livedata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import io.github.caoshen.baselib.base.BaseApp

class NetworkWatchLiveData : LiveData<NetworkInfo?>() {
    companion object {
        private lateinit var sInstance: NetworkWatchLiveData

        @MainThread
        fun get(): NetworkWatchLiveData {
            sInstance = if (::sInstance.isInitialized) {
                sInstance
            } else {
                NetworkWatchLiveData()
            }
            return sInstance
        }
    }

    private val mContext: Context = BaseApp.instance

    private val mNetworkReceiver: NetworkReceiver = NetworkReceiver()

    private val mIntentFilter: IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

    override fun onActive() {
        super.onActive()
        mContext.registerReceiver(mNetworkReceiver, mIntentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        mContext.unregisterReceiver(mNetworkReceiver)
    }

    private class NetworkReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context == null) {
                return
            }
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = manager.activeNetworkInfo
            get().postValue(activeNetworkInfo)
        }

    }
}