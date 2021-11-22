package io.github.caoshen.androidadvance.jetpack.viewmodel

import io.github.caoshen.androidadvance.jetpack.net.WxArticleRepository
import io.github.caoshen.baselib.base.BaseViewModel

class ApiViewModel: BaseViewModel() {

    private val repository by lazy {
        WxArticleRepository()
    }
}