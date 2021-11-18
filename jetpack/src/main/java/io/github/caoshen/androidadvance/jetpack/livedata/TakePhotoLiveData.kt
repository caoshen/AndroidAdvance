package io.github.caoshen.androidadvance.jetpack.livedata

import android.graphics.Bitmap
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData

class TakePhotoLiveData(
    private val registry: ActivityResultRegistry,
    private val key: String
) : LiveData<Bitmap>() {

    private lateinit var mTakePhotoLauncher: ActivityResultLauncher<Void>

    override fun onActive() {
        super.onActive()
        mTakePhotoLauncher = registry.register(key, ActivityResultContracts.TakePicturePreview()) {
            value = it
        }
    }

    override fun onInactive() {
        super.onInactive()
        mTakePhotoLauncher.unregister()
    }

    fun takePhoto() {
        mTakePhotoLauncher.launch(null)
    }
}