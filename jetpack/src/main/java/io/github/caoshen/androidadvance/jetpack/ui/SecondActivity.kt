package io.github.caoshen.androidadvance.jetpack.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivitySecondBinding
import io.github.caoshen.baselib.base.BaseActivity
import io.github.caoshen.baselib.utils.toast
import java.util.jar.Manifest

class SecondActivity : BaseActivity(R.layout.activity_second) {
    private val mBinding by viewBinding(ActivitySecondBinding::bind)

    private val mRequestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            toast("request permission $it")
        }

    override fun init() {
        mBinding.btnBack.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().putExtra("key", "message"))
            finish()
        }

        mBinding.btnRequestPermission.setOnClickListener {
            mRequestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}