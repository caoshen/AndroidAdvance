package io.github.caoshen.androidadvance.jetpack.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
    }

    val hasMoreStateLiveData: MutableLiveData<HasMoreState> = MutableLiveData()

    val loadStateLiveData: MutableLiveData<LoadState> = MutableLiveData()

    open fun init() {

    }

    protected fun loadStart() {
        loadStateLiveData.value = LoadState.LOAD_START
    }

    protected fun loadFinish(success: Boolean) {
        if (success) {
            loadStateLiveData.value = LoadState.LOAD_SUCCESS
        } else {
            loadStateLiveData.value = LoadState.LOAD_FAIL
        }
    }

    protected fun hasMore(hasMore: Boolean) =
            if (hasMore) {
                hasMoreStateLiveData.value = HasMoreState.HAS_MORE
            } else {
                hasMoreStateLiveData.value = HasMoreState.NO_MORE
            }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: " + javaClass.name + ", " + this)
    }
}