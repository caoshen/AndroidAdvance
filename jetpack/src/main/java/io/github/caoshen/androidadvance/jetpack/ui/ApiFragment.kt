package io.github.caoshen.androidadvance.jetpack.ui

import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.FragmentApiBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.ApiViewModel
import io.github.caoshen.baselib.base.BaseFragment

class ApiFragment : BaseFragment(R.layout.fragment_api) {

    private val mBinding by viewBinding(FragmentApiBinding::bind)

    private val mViewModel by activityViewModels<ApiViewModel>()
}