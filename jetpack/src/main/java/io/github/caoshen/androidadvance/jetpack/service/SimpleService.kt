package io.github.caoshen.androidadvance.jetpack.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.github.caoshen.androidadvance.jetpack.ISimpleService

class SimpleService : Service() {
    companion object {
        const val TAG = "SimpleService"
    }

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
}