package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LiveDataViewModel : ViewModel() {

    object LiveDataVMFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return LiveDataViewModel() as T
        }
    }

}
