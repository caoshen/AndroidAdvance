package io.github.caoshen.androidadvance.jetpack.livedata

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData

class RequestPermissionLiveData(
    private val registry: ActivityResultRegistry,
    private val key: String
) : LiveData<Boolean>() {

    private lateinit var mRequestPermissionLauncher: ActivityResultLauncher<String>

    override fun onActive() {
        super.onActive()
        mRequestPermissionLauncher = registry.register(
            key,
            ActivityResultContracts.RequestPermission()
        ) {
            value = it
        }
    }

    override fun onInactive() {
        super.onInactive()
        mRequestPermissionLauncher.unregister()
    }

    fun requestPermission(permission: String) {
        mRequestPermissionLauncher.launch(permission)
    }
}