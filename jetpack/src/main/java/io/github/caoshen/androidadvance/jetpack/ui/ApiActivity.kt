package io.github.caoshen.androidadvance.jetpack.ui

import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityApiBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.ApiViewModel
import io.github.caoshen.baselib.base.BaseActivity

class ApiActivity : BaseActivity(R.layout.activity_api) {
    companion object {
        private const val TAG = "ApiActivity"
    }

    private val mBinding by viewBinding(ActivityApiBinding::bind)

    private val mViewModel by viewModels<ApiViewModel>()

    override fun init() {
        initData()
        initObserver()
    }

    private fun initObserver() {
        mViewModel.wxArticleLiveData.observeState(this) {
            onSuccess {
                showNetErrorPic(false)
                mBinding.tvContent.text = it.toString()
            }

            onFailed { code, msg ->
                mBinding.tvContent.text = "code:$code, msg:$msg"
            }

            onException {
                showNetErrorPic(true)
            }

            onEmpty {
            }

            onComplete {
                dismissLoading()
            }
        }

        mViewModel.mediatorLiveData.observe(this) {
            showNetErrorPic(false)
            mBinding.tvContent.text = it.toString()
        }

        mViewModel.userLiveData.observeState(this) {
            onSuccess {
                mBinding.tvContent.text = it.toString()
            }

            onComplete {
                dismissLoading()
            }
        }
    }

    private fun showNetErrorPic(isShowError: Boolean) {
        mBinding.tvContent.isGone = isShowError
        mBinding.ivContent.isVisible = isShowError
    }

    private fun initData() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, ApiFragment())
            .commit()

        mBinding.btnNet.setOnClickListener {
            showLoading()
            mViewModel.requestNet()
        }

        mBinding.btnNetError.setOnClickListener {
            showLoading()
            mViewModel.requestNetError()
        }

        mBinding.btnMediatorNet.setOnClickListener {
            mBinding.tvContent.text = ""
            mViewModel.requestFromNet()
        }

        mBinding.btnMediatorDb.setOnClickListener {
            mBinding.tvContent.text = ""
            mViewModel.requestFromDb()
        }

        mBinding.btnLogin.setOnClickListener {
            showLoading()
            mBinding.tvContent.text = ""
            mViewModel.login("FastJetpack", "FastJetpack")
        }
    }
}