package io.github.caoshen.androidadvance.jetpack.livedata

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

inline fun Fragment.requestPermission(
    permission: String,
    crossinline granted: (String) -> Unit = {},
    crossinline denied: (String) -> Unit = {},
    crossinline explained: (String) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        when {
            it -> {
                granted.invoke(permission)
            }
            shouldShowRequestPermissionRationale(permission) -> {
                denied.invoke(permission)
            }
            else -> {
                explained.invoke(permission)
            }
        }
    }.launch(permission)
}

inline fun AppCompatActivity.requestPermission(
    permission: String,
    crossinline granted: (String) -> Unit = {},
    crossinline denied: (String) -> Unit = {},
    crossinline explained: (String) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        when {
            it -> {
                granted.invoke(permission)
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, permission) -> {
                denied.invoke(permission)
            }
            else -> {
                explained.invoke(permission)
            }
        }
    }.launch(permission)
}
