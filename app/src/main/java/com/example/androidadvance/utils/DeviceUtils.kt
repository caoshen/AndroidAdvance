package com.example.androidadvance.utils

import android.util.Log
import java.util.*

object DeviceUtils {
    const val TAG = "DeviceUtils"

    fun getDeviceId(): String {
        val context = BaseLibrary.getContext()
        context ?: Log.d("", "context is null")
        return UUID.randomUUID().toString()
    }

}
