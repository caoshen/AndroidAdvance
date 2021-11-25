package io.github.caoshen.androidadvance.jetpack.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.FragmentApiBinding
import io.github.caoshen.androidadvance.jetpack.livedata.TimerGlobalLiveData
import io.github.caoshen.androidadvance.jetpack.viewmodel.ApiViewModel
import io.github.caoshen.baselib.base.BaseFragment

class ApiFragment : BaseFragment(R.layout.fragment_api) {
    companion object {
        const val TAG = "ApiFragment"
    }

    private val mBinding by viewBinding(FragmentApiBinding::bind)

    private val mViewModel by activityViewModels<ApiViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        mViewModel.wxArticleLiveData.observeState(this) {
            onSuccess {
                if (it.size >= 2) {
                    val wxArticleBean = it[0]
                    val wxArticleNext = it[1]
                    mBinding.textApi.text =
                        "name:${wxArticleBean.name} is display:${wxArticleBean.visible}\n" +
                                "name:${wxArticleNext.name} is display:${wxArticleNext.visible}"
                }
            }
        }

        TimerGlobalLiveData.get().observe(viewLifecycleOwner) {
            Log.i(TAG, "Global timer: value: == $it")
        }
    }
}