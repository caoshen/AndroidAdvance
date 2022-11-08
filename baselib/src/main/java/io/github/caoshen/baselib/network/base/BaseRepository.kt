package io.github.caoshen.baselib.network.base

import android.util.Log
import io.github.caoshen.baselib.BuildConfig
import io.github.caoshen.baselib.network.entity.*
import io.github.caoshen.baselib.network.entity.handlingExceptions
import kotlinx.coroutines.delay

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
}