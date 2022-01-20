package io.github.caoshen.baselib.utils

import android.content.Context
import android.os.Build
import android.text.TextUtils
import androidx.preference.PreferenceManager
import java.lang.Exception
import java.util.*

object DeviceUtils {
    private val KEY_DEVICE_ID: String = "device_id"

    // md5 for unknown devices
    val IGNORE_DEVICE_IDS: Set<String> = setOf("ad921d60486366258809553a3db49a4a")

    val ERROR_SERIAL: Set<String> = setOf(
        "02:00:00:00:00:00",
        "00:00:00:00:00:00",
        "ff:ff:ff:ff:ff:ff",
        "FF:FF:FF:FF:FF:FF"
    )

    fun getDeviceId(context: Context, capability: Capability): String? {
        // androidx.preference
        var deviceId = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_DEVICE_ID, "")

        // has local id
        if (!TextUtils.isEmpty(deviceId) && !IGNORE_DEVICE_IDS.contains(deviceId)) {
            return deviceId
        }

        // try oaid
        deviceId = capability.getOaid()
        // try get serial
        if (TextUtils.isEmpty(deviceId) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val serial = Build.getSerial()
                deviceId = getMd5(serial)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        // try uuid
        if (TextUtils.isEmpty(deviceId) || isErrorSerial(deviceId)) {
            val uuid = UUID.randomUUID().toString()
            deviceId = getMd5(uuid)
        }

        // save it
        if (!TextUtils.isEmpty(deviceId)) {
                PreferenceManager.getDefaultSharedPreferences(context)
                    .edit()
                    .putString(KEY_DEVICE_ID, deviceId)
                    .apply()
        }
        return deviceId
    }

    private fun isErrorSerial(deviceId: String?): Boolean {
        return ERROR_SERIAL.contains(deviceId)
    }
}