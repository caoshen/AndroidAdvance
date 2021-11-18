package io.github.caoshen.androidadvance.jetpack.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivitySecondBinding
import io.github.caoshen.androidadvance.jetpack.livedata.RequestPermissionLiveData
import io.github.caoshen.androidadvance.jetpack.livedata.TakePhotoLiveData
import io.github.caoshen.androidadvance.jetpack.livedata.TimerGlobalLiveData
import io.github.caoshen.baselib.base.BaseActivity
import io.github.caoshen.baselib.utils.toast

class SecondActivity : BaseActivity(R.layout.activity_second) {
    private val mBinding by viewBinding(ActivitySecondBinding::bind)

    private val mTakePhotoLiveData: TakePhotoLiveData = TakePhotoLiveData(
        activityResultRegistry,
        "key"
    )

    private val mRequestPermissionLiveData: RequestPermissionLiveData = RequestPermissionLiveData(
        activityResultRegistry,
        "key_permission"
    )

    private val mRequestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            toast("request location permission $it")
        }

    override fun init() {
        // back with result
        mBinding.btnBack.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().putExtra("key", "message"))
            finish()
        }

        // take picture and preview with activity result launcher
        mTakePhotoLiveData.observe(this) {
            mBinding.imageView.setImageBitmap(it)
        }
        mBinding.btnTakePhoto.setOnClickListener {
            mTakePhotoLiveData.takePhoto()
        }

        // request permissions with activity result launcher
        mBinding.btnRequestPermission.setOnClickListener {
            mRequestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        // request permissions with live data
        mRequestPermissionLiveData.observe(this) {
            toast("record audio permission $it")
        }
        mBinding.btnRequestPermissionLivedata.setOnClickListener {
            mRequestPermissionLiveData.requestPermission(Manifest.permission.RECORD_AUDIO)
        }

        // global timer live data
        TimerGlobalLiveData.get().observe(this) {
            toast("value $it")
        }
        mBinding.btnStopTimer.setOnClickListener {
            TimerGlobalLiveData.get().stopTimer()
            toast("stop global timer.")
        }

    }
}