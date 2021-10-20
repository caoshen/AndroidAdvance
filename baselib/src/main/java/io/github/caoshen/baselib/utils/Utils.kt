package io.github.caoshen.baselib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import io.github.caoshen.baselib.base.BaseApp
import java.util.*

inline fun <reified T : Activity> Activity.startActivity(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}

fun stringOf(@StringRes id: Int, vararg formatArgs: Any): String = getString(id, *formatArgs)

fun stringOf(@StringRes id: Int): String = getString(id)

fun getString(@StringRes id: Int, vararg formatArgs: Any): String {
    return BaseApp.instance.getString(id, formatArgs)
}

fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApp.instance, message, duration).show()
}

fun EditText.getNonNullText(): String = text?.toString()?.trim() ?: ""

fun EditText.getNonNullUpperCaseText(): String = getNonNullText().uppercase(Locale.getDefault())

fun EditText.openKeyBoard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.run {
        showSoftInput(this@openKeyBoard, InputMethodManager.RESULT_SHOWN)
        toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun EditText.hideKeyBoard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.run {
        hideSoftInputFromWindow(
            this@hideKeyBoard.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }
}

fun View.clickWithLimit(intervalMill: Int = 500, block: ((v: View?) -> Unit)) {
    setOnClickListener(object : View.OnClickListener {
        var last = 0L
        override fun onClick(v: View?) {
            if (System.currentTimeMillis() - last > intervalMill) {
                block(v)
                last = System.currentTimeMillis()
            }
        }
    })
}