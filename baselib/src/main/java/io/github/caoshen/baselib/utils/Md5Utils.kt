package io.github.caoshen.baselib.utils

import android.os.Build
import android.text.TextUtils
import okhttp3.internal.and
import okhttp3.internal.toHexString
import java.lang.Exception
import java.security.MessageDigest

fun getMd5(str: String): String? {
    if (TextUtils.isEmpty(str) || str == Build.UNKNOWN) {
        return null
    }

    val hash: ByteArray
    try {
        hash = MessageDigest.getInstance("MD5")
            .digest(str.toByteArray(Charsets.UTF_8))
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    return buildString {
        for (bt in hash.asList()) {
            val i: Int = bt and 0xFF
            append(if (i < 0x10) "0${i.toHexString()}" else i.toHexString())
        }
    }
}