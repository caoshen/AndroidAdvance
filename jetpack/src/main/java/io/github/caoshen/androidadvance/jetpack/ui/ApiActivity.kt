package io.github.caoshen.androidadvance.jetpack.ui

import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityApiBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.ApiViewModel
import io.github.caoshen.baselib.base.BaseActivity

class ApiActivity : BaseActivity(R.layout.activity_api) {
    private val mBinding by viewBinding(ActivityApiBinding::bind)

    private val mViewModel by viewModels<ApiViewModel>()

    override fun init() {
        initData()
        initObserver()
    }

    private fun initObserver() {
        mViewModel.wxArticleLiveData.observeState(this) {
            onSuccess {

            }
        }
    }

    private fun initData() {
        mBinding.btnNet.setOnClickListener {
            showLoading()
            mViewModel.requestNet()
        }
    }
}