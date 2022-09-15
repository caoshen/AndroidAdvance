package io.github.caoshen.androidadvance.jetpack.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

open class PackageMonitor : BroadcastReceiver() {
    private var isRegistered: Boolean = false

    companion object {
        private const val TAG = "PackageMonitor"
        private const val EXTRA_REMOVED_FOR_ALL_USERS = "android.intent.extra.REMOVED_FOR_ALL_USERS"
        private const val ACTION_QUERY_PACKAGE_RESTART = "android.intent.action.QUERY_PACKAGE_RESTART"
        private const val EXTRA_PACKAGES = "android.intent.extra.PACKAGES"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null) {
            return
        }
        val action = intent.action
        Log.d(TAG, "onReceive: action=$action")

        when (action) {
            Intent.ACTION_PACKAGE_ADDED -> {
                val packageName = getPackageName(intent = intent)
                Log.d(TAG, "package added:$packageName")
                if (intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)) {
                    onPackageModified(packageName)
                } else {
                    onPackageAdded(packageName)
                }
                onPackageAppeared(packageName)
            }
            Intent.ACTION_PACKAGE_REMOVED -> {
                val packageName = getPackageName(intent = intent)
                Log.d(TAG, "package removed:$packageName")
                if (intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)) {
                    onPackageUpdateStarted(packageName)
                } else {
                    onPackageRemoved(packageName)
                    if (intent.getBooleanExtra(EXTRA_REMOVED_FOR_ALL_USERS, false)) {
                        onPackageRemovedAllUsers(packageName)
                    }
                }
                onPackageDisappeared(packageName)
            }
            Intent.ACTION_PACKAGE_CHANGED -> {
                val packageName = getPackageName(intent = intent)
                onPackageModified(packageName)
            }
            Intent.ACTION_DATE_CHANGED -> {
                val packageName = getPackageName(intent = intent)
                onPackageDataCleared(packageName)
            }
            ACTION_QUERY_PACKAGE_RESTART -> {
                val disappearingPackages = intent.getStringArrayExtra(EXTRA_PACKAGES)
                if (disappearingPackages != null) {
                    onHandleForceStop(disappearingPackages)
                }
            }
            Intent.ACTION_PACKAGE_RESTARTED -> {
                val packageName = getPackageName(intent = intent)
                val disappearingPackages = arrayOf(packageName)
                onHandleForceStop(disappearingPackages)
            }
        }
    }

    open fun onHandleForceStop(disappearingPackages: Array<String?>) {
    }

    open fun onPackageDataCleared(packageName: String?) {
    }

    open fun onPackageRemovedAllUsers(packageName: String?) {
    }

    open fun onPackageDisappeared(packageName: String?) {

    }

    open fun onPackageRemoved(packageName: String?) {
    }

    open fun onPackageUpdateStarted(packageName: String?) {

    }

    open fun onPackageAdded(packageName: String?) {
    }

    open fun onPackageModified(packageName: String?) {
    }

    open fun onPackageAppeared(packageName: String?) {
    }

    private fun getPackageName(intent: Intent): String? {
        val uri = intent.data
        return uri?.schemeSpecificPart
    }

    fun register(context: Context) {
        if (isRegistered) {
            Log.d(TAG, "already registered.")
            return
        }
        isRegistered = true
        val packageIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addAction(Intent.ACTION_PACKAGE_REPLACED)
            addAction(Intent.ACTION_MY_PACKAGE_REPLACED)
            addAction(Intent.ACTION_PACKAGE_CHANGED)
            addAction(ACTION_QUERY_PACKAGE_RESTART)
            addAction(Intent.ACTION_PACKAGE_RESTARTED)
            addAction(Intent.ACTION_PACKAGE_DATA_CLEARED)
            addDataScheme("package")
        }

        context.registerReceiver(this, packageIntentFilter)
    }

    fun unregister(context: Context) {
        if (!isRegistered) {
            Log.d(TAG, "Not registered.")
            return
        }
        isRegistered = false
        context.unregisterReceiver(this)
    }

}