package io.github.caoshen.androidadvance.jetpack.livedata

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

class TimerGlobalLiveData : LiveData<Int>() {

    companion object {
        private lateinit var sInstance: TimerGlobalLiveData

        private var mCount = 0

        @MainThread
        fun get(): TimerGlobalLiveData {
            sInstance = if (::sInstance.isInitialized) {
                sInstance
            } else {
                TimerGlobalLiveData()
            }
            return sInstance
        }
    }

    private val mTimerRunnable: Runnable = object : Runnable {
        override fun run() {
            postValue(mCount++)
            mHandler.postDelayed(this, 1000L)
        }
    }

    private val mHandler: Handler = Handler(Looper.getMainLooper())

    fun startTimer() {
        mCount = 0
        mHandler.postDelayed(mTimerRunnable, 1000L)
    }

    fun stopTimer() {
        mHandler.removeCallbacks(mTimerRunnable)
    }
}