package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LiveDataViewModel : ViewModel() {

    object LiveDataVMFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LiveDataViewModel() as T
        }
    }

}
