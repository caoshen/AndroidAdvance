package io.github.caoshen.androidadvance.jetpack.ui

import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivitySavedStateBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.SavedStateViewModel
import io.github.caoshen.baselib.base.BaseActivity
import io.github.caoshen.baselib.network.utils.toast

class SavedStateActivity : BaseActivity(R.layout.activity_saved_state) {

    private val mBinding by viewBinding(ActivitySavedStateBinding::bind)

    private val mViewModel by viewModels<SavedStateViewModel>()

    override fun init() {

        val name = mViewModel.userName
        val value = mViewModel.inputLiveData.value.toString()

        toast("livedata value $value, name:$name")

        mBinding.btnSubmit.setOnClickListener {
            val inputText = mBinding.editTextInput.text.toString()
            mViewModel.inputLiveData.value = inputText

            mViewModel.userName = "Hello, World!"
        }
    }
}