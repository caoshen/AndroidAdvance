package io.github.caoshen.androidadvance.jetpack.net

import android.util.Log
import io.github.caoshen.androidadvance.jetpack.bean.User
import io.github.caoshen.androidadvance.jetpack.bean.WxArticleBean
import io.github.caoshen.baselib.network.base.BaseRepository
import io.github.caoshen.baselib.network.entity.ApiResponse
import io.github.caoshen.baselib.network.entity.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class WxArticleRepository : BaseRepository() {
    companion object {
        private const val TAG = "WxArticleRepository"
    }

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun fetchWxArticleByFlow() {
        fetch("${ApiService.BASE_URL}/wxarticle/chapters/json")
            .catch {
                Log.d(TAG, "There ware a problem:$it")
            }
            .collect {
                Log.d(TAG, "response:$it")
            }
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
            ApiSuccessResponse(arrayListOf(wxArticleBean))
        }
}