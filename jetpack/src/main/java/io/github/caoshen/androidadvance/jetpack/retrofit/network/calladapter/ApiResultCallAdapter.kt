package io.github.caoshen.androidadvance.jetpack.retrofit

import io.github.caoshen.androidadvance.jetpack.retrofit.network.exception.ApiException
import io.github.caoshen.androidadvance.jetpack.retrofit.network.ApiError
import io.github.caoshen.androidadvance.jetpack.retrofit.network.ApiResult
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.UnsupportedOperationException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author caoshen
 * @date 2021/1/27
 */
class ApiResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        check(getRawType(returnType) == Call::class.java)

        check(returnType is ParameterizedType)

        val apiResultType = getParameterUpperBound(0, returnType)
        check(getRawType(apiResultType) == ApiResult::class.java)
        check(apiResultType is ParameterizedType)

        val dataType = getParameterUpperBound(0, apiResultType)
        return ApiResultCallAdapter<Any>(dataType)
    }
}

class ApiResultCallAdapter<T>(private val type: Type) : CallAdapter<T, Call<ApiResult<T>>> {
    override fun responseType(): Type = type

    override fun adapt(call: Call<T>): Call<ApiResult<T>> {
        return ApiResultCall(call)
    }

}

class ApiResultCall<T>(private val delegate: Call<T>) : Call<ApiResult<T>> {
    override fun clone(): Call<ApiResult<T>> = ApiResultCall(delegate.clone())

    override fun execute(): Response<ApiResult<T>> {
        throw UnsupportedOperationException("ApiResultCall does not support synchronous execution")
    }

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val apiResult = if (response.body() == null) {
                        // data null error
                        ApiResult.Failure(ApiError.dataIsNull.errorCode, ApiError.dataIsNull.errorMsg)
                    } else {
                        // success
                        ApiResult.Success(response.body()!!)
                    }
                    callback.onResponse(this@ApiResultCall, Response.success(apiResult))
                } else {
                    // http server error
                    val failureApiResult = ApiResult.Failure(ApiError.httpStatusCodeError.errorCode,
                            ApiError.httpStatusCodeError.errorMsg)
                    callback.onResponse(this@ApiResultCall, Response.success(failureApiResult))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val failureApiResult = if (t is ApiException) {
                    // business exception
                    ApiResult.Failure(t.errorCode, t.errorMsg)
                } else {
                    // network not available
                    ApiResult.Failure(ApiError.unknownException.errorCode,
                            ApiError.unknownException.errorMsg)
                }
                callback.onResponse(this@ApiResultCall, Response.success(failureApiResult))
            }
        })
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun request(): Request {
        return request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }

}