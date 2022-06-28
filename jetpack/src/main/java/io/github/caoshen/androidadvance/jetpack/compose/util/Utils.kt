package io.github.caoshen.androidadvance.jetpack.compose.util

import android.content.Context
import android.widget.Toast
import io.github.caoshen.androidadvance.jetpack.R

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}