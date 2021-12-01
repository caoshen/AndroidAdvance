package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.caoshen.androidadvance.jetpack.livedata.StockLiveData

class NameViewModel : ViewModel() {
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val stockLiveData = StockLiveData("1")

    fun onUserClick() {
        val cur: String? = currentName.value
        val next: Int = if (cur != null) {
            cur.toInt() + 1
        } else {
            1
        }
        currentName.value = next.toString()
    }
}