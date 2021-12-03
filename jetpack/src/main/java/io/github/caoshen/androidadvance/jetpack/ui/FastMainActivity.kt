package io.github.caoshen.androidadvance.jetpack.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityFastMainBinding
import io.github.caoshen.androidadvance.jetpack.livedata.NetworkWatchLiveData
import io.github.caoshen.androidadvance.jetpack.livedata.TimerGlobalLiveData
import io.github.caoshen.baselib.base.BaseActivity
import io.github.caoshen.baselib.utils.startActivity
import io.github.caoshen.baselib.utils.toast

class FastMainActivity : BaseActivity(R.layout.activity_fast_main) {
    companion object {
        private const val TAG = "FastMainActivity"
    }

    private val mBinding by viewBinding(ActivityFastMainBinding::bind)

    private val mActivityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                toast(it.data?.getStringExtra("key") ?: "")
            }
        }

    override fun init() {
        initData()
        initGlobalObserver()
    }

    private fun initGlobalObserver() {
        TimerGlobalLiveData.get().observe(this) {
            Log.i(TAG, "global timer: $it")
        }

        NetworkWatchLiveData.get().observe(this) {
            Log.i(TAG, "network change: $it")
        }
    }

    private fun initData() {
        mBinding.btnApiActivity.setOnClickListener {
            startActivity<ApiActivity>()
        }

        mBinding.btnSecondActivity.setOnClickListener {
            mActivityResultLauncher.launch(Intent(this, SecondActivity::class.java))
        }

        mBinding.btnSaveStateActivity.setOnClickListener {
            startActivity<SavedStateActivity>()
        }

        mBinding.btnStartTimer.setOnClickListener {
            TimerGlobalLiveData.get().startTimer()
            toast("start timer.")
        }

        mBinding.btnLivedata.setOnClickListener {
            startActivity<NameActivity>()
        }

        mBinding.btnAudioFocus.setOnClickListener {
            startActivity<AudioFocusActivity>()
        }
    }

}