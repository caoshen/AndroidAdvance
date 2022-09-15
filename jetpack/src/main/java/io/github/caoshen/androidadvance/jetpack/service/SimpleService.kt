package io.github.caoshen.androidadvance.jetpack.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.github.caoshen.androidadvance.jetpack.ISimpleService
import io.github.caoshen.androidadvance.jetpack.receiver.PackageMonitor

class SimpleService : Service() {
    companion object {
        const val TAG = "SimpleService"
    }

    private var packageMonitor = SimplePackageMonitor()

    private val mBinder: ISimpleService.Stub = object : ISimpleService.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
            Log.e(TAG, "int=$anInt")
        }

        override fun printName(name: String?) {
            Log.e(TAG, "print name=$name")
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        packageMonitor.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        packageMonitor.unregister(this)
    }

    class SimplePackageMonitor : PackageMonitor() {
        override fun onHandleForceStop(disappearingPackages: Array<String?>) {
            super.onHandleForceStop(disappearingPackages)
            Log.d(TAG, "onHandleForceStop:$disappearingPackages")
        }

        override fun onPackageDataCleared(packageName: String?) {
            super.onPackageDataCleared(packageName)
            Log.d(TAG, "onPackageDataCleared:$packageName")
        }

        override fun onPackageRemovedAllUsers(packageName: String?) {
            super.onPackageRemovedAllUsers(packageName)
            Log.d(TAG, "onPackageRemovedAllUsers:$packageName")
        }

        override fun onPackageDisappeared(packageName: String?) {
            super.onPackageDisappeared(packageName)
            Log.d(TAG, "onPackageDisappeared:$packageName")
        }

        override fun onPackageRemoved(packageName: String?) {
            super.onPackageRemoved(packageName)
            Log.d(TAG, "onPackageRemoved:$packageName")
        }

        override fun onPackageUpdateStarted(packageName: String?) {
            super.onPackageUpdateStarted(packageName)
            Log.d(TAG, "onPackageUpdateStarted:$packageName")
        }

        override fun onPackageAdded(packageName: String?) {
            super.onPackageAdded(packageName)
            Log.d(TAG, "onPackageAdded:$packageName")
        }

        override fun onPackageModified(packageName: String?) {
            super.onPackageModified(packageName)
            Log.d(TAG, "onPackageModified:$packageName")

        }

        override fun onPackageAppeared(packageName: String?) {
            super.onPackageAppeared(packageName)
            Log.d(TAG, "onPackageAppeared:$packageName")

        }
    }
}