package io.github.caoshen.androidadvance.jetpack.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityLivedataBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.LiveDataViewModel
import io.github.caoshen.androidadvance.jetpack.mvvm.base.BaseActivity

// https://github.com/android/architecture-components-samples/tree/main/LiveDataSample
class LiveDataActivity : BaseActivity() {

    private val viewModel: LiveDataViewModel by viewModels { LiveDataViewModel.LiveDataVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLivedataBinding>(this, R.layout.activity_livedata)

        // lifecycle owner
        binding.lifecycleOwner = this
        // bind viewModel
        binding.viewmodel = viewModel
    }
}