package io.github.caoshen.baselib.network.base

import android.util.Log
import io.github.caoshen.baselib.BuildConfig
import io.github.caoshen.baselib.network.entity.*
import io.github.caoshen.baselib.network.entity.handlingExceptions
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.*
import java.io.IOException

/**
 * 两种方式封装Retrofit+协程，实现优雅快速的网络请求
 * https://juejin.cn/post/6993294489125126151
 */
open class BaseRepository {

    suspend fun <T> executeHttp(block: suspend () -> ApiResponse<T>): ApiResponse<T> {
        // for test
        delay(500)
        runCatching {
            block.invoke()
        }.onSuccess { data ->
            return handleHttpOk(data)
        }.onFailure { e ->
            return handleHttpError(e)
        }
        return ApiEmptyResponse()
    }

    private fun <T> handleHttpError(e: Throwable): ApiResponse<T> {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
        handlingExceptions(e)
        return ApiErrorResponse(e)
    }

    private fun <T> handleHttpOk(response: ApiResponse<T>): ApiResponse<T> {
        return if (response.isSuccess) {
            getHttpSuccessResponse(response)
        } else {
            handlingApiExceptions(response.errorCode, response.errorMsg)
            ApiFailedResponse(response.errorCode, response.errorMsg)
        }
    }

    private fun <T> getHttpSuccessResponse(response: ApiResponse<T>): ApiResponse<T> {
        val data = response.data
        return if (data == null || (data is List<*> && (data as List<*>).isEmpty())) {
            ApiEmptyResponse()
        } else {
            ApiSuccessResponse(data)
        }
    }

    /**
     * OkHTTP with Kotlin Flows and Coroutines
     * https://mmmnnnmmm.com/okhttp-kotlin-flow-coroutine.html
     */
    fun fetch(
        url: String,
        okHttpClient: OkHttpClient = BaseOkHttpClient.get(),
        request: Request.Builder = Request.Builder()
    ) = callbackFlow<Response> {
        val req = request.url(url)
            .build()
        okHttpClient.newCall(req)
            .enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    cancel("okhttp error", e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        trySendBlocking(response)
                    } else {
                        cancel("bad http code")
                    }
                }
            })
    }
}