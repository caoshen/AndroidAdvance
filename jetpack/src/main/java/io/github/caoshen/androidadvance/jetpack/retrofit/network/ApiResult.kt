package io.github.caoshen.androidadvance.jetpack.retrofit.network

/**
 * @author caoshen
 * @date 2021/1/27
 */
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val errorCode: Int, val errorMsg: String) : ApiResult<Nothing>()
}