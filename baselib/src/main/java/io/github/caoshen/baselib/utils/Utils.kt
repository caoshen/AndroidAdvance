package io.github.caoshen.baselib.utils

import android.widget.Toast
import io.github.caoshen.baselib.base.BaseApp

fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApp.instance, message, duration).show()
}