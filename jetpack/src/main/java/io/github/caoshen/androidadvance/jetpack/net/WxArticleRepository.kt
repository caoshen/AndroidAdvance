package io.github.caoshen.androidadvance.jetpack.net

import io.github.caoshen.androidadvance.jetpack.bean.User
import io.github.caoshen.androidadvance.jetpack.bean.WxArticleBean
import io.github.caoshen.baselib.network.base.BaseRepository
import io.github.caoshen.baselib.network.entity.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WxArticleRepository : BaseRepository() {
    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun fetchWxArticleFromNet(): ApiResponse<List<WxArticleBean>> {
        return executeHttp {
            mService.getWxArticle()
        }
    }

    suspend fun fetchWxArticleFromDb(): ApiResponse<List<WxArticleBean>> {
        return getWxArticleFromDatabase()
    }

    suspend fun fetchWxArticleError(): ApiResponse<List<WxArticleBean>> {
        return executeHttp {
            mService.getWxArticleError()
        }
    }

    suspend fun login(userName: String, password: String): ApiResponse<User?> {
        return executeHttp {
            mService.login(userName, password)
        }
    }

    private suspend fun getWxArticleFromDatabase(): ApiResponse<List<WxArticleBean>> =
        withContext(Dispatchers.IO) {
            val wxArticleBean = WxArticleBean(999, "zhangsan", 1)
            ApiResponse(arrayListOf(wxArticleBean))
        }
}