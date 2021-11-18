package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        const val SAVE_STATE_KEY_STRING = "user_name"
        const val SAVE_STATE_KEY_LIVE_DATA = "input_livedata"
    }

    var userName: String?
        get() = savedStateHandle.get(SAVE_STATE_KEY_STRING)
        set(value) = savedStateHandle.set(SAVE_STATE_KEY_STRING, value)

    val inputLiveData: MutableLiveData<String> =
        savedStateHandle.getLiveData<String>(SAVE_STATE_KEY_LIVE_DATA)

}