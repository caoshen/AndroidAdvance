package io.github.caoshen.androidadvance.jetpack.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.caoshen.androidadvance.jetpack.retrofit.network.ApiResult
import kotlinx.coroutines.launch

/**
 * @author caoshen
 * @date 2021/1/27
 */
class MainViewModel : ViewModel() {
    private val _translateResult: MutableLiveData<String> = MutableLiveData()

    /**
     * expose translate result
     */
    val translateResult: LiveData<String>
        get() = _translateResult

    fun translate(word: String) {
        try {
            viewModelScope.launch {
                val result = TranslateApi.retrofitService.translate(word)

                val text: String = when (result) {
                    is ApiResult.Success -> result.data.translateResult[0][0].tgt
                    is ApiResult.Failure -> "errorCode:${result.errorCode} errorMsg:${result.errorMsg}"
                }
                _translateResult.value = text
            }
        } catch (exception: Exception) {
            _translateResult.value = exception.message
        }
    }
}