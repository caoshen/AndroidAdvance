package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * LiveData 连续 postValue 两次，第一次的值会丢失
 */
class MyViewModel : ViewModel() {
    val countLiveData = MutableLiveData<String>()

    var title: String = ""

    /**
     * LiveData postValue
     *
     * If you called this method multiple times before a main thread executed a posted task,
     * only the last value would be dispatched.
     */
    fun updateTitlePost(str: String) {
        title = str
        countLiveData.postValue(title)
    }
}