package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import io.github.caoshen.androidadvance.jetpack.bean.User
import io.github.caoshen.androidadvance.jetpack.bean.WxArticleBean
import io.github.caoshen.androidadvance.jetpack.net.WxArticleRepository
import io.github.caoshen.baselib.base.BaseViewModel
import io.github.caoshen.baselib.network.entity.ApiResponse
import io.github.caoshen.baselib.network.observer.StateLiveData
import kotlinx.coroutines.launch

class ApiViewModel : BaseViewModel() {

    private val repository by lazy {
        WxArticleRepository()
    }

    val wxArticleLiveData = StateLiveData<List<WxArticleBean>>()

    val userLiveData = StateLiveData<User?>()

    private val dbLiveData = StateLiveData<List<WxArticleBean>>()

    private val apiLiveData = StateLiveData<List<WxArticleBean>>()

    val mediatorLiveData: MediatorLiveData<ApiResponse<List<WxArticleBean>>> =
        MediatorLiveData<ApiResponse<List<WxArticleBean>>>()
            .apply {
                addSource(apiLiveData) {
                    this.value = it
                }
                addSource(dbLiveData) {
                    this.value = it
                }
            }

    fun requestNet() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

    fun requestNetError() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleError()
        }
    }

    fun requestFromNet() {
        viewModelScope.launch {
            apiLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

    fun requestFromDb() {
        viewModelScope.launch {
            dbLiveData.value = repository.fetchWxArticleFromDb()
        }
    }

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            userLiveData.value = repository.login(userName, password)
        }
    }
}