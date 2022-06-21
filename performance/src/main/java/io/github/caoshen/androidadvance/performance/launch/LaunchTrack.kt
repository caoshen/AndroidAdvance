package io.github.caoshen.androidadvance.performance.launch

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import io.github.caoshen.androidadvance.performance.ITrack

object LaunchTrack : ITrack {

    private var isLaunched = false

    private var lastActivityPauseTime = 0L

    private val uiHandler = Handler(Looper.getMainLooper())

    private val activityLifecycleCallbacks : Application.ActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            isLaunched = true

            // 上一个 activity pause 的时刻。如果不存在，就使用 activity create 的时刻。
            if (lastActivityPauseTime == 0L) {
                lastActivityPauseTime = SystemClock.uptimeMillis()
            }

            // 考虑 activity 启动时 finish 的情况。比如开屏界面。
            val curTime = lastActivityPauseTime
            if (activity.isFinishing) {
                uiHandler.post {
                    collectInfo(activity, curTime, true)
                    lastActivityPauseTime = SystemClock.uptimeMillis()
                }
            }
        }

        override fun onActivityStarted(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityResumed(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityPaused(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityStopped(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            TODO("Not yet implemented")
        }

        override fun onActivityDestroyed(activity: Activity) {
            TODO("Not yet implemented")
        }

    }

    private fun collectInfo(activity: Activity, curTime: Long, b: Boolean) {
        TODO("Not yet implemented")
    }

    fun startTrack() {

    }

    fun stopTrack() {

    }

    override fun startTrack(application: Application) {
        TODO("Not yet implemented")
    }

    override fun stopTrack(application: Application) {
        TODO("Not yet implemented")
    }
}