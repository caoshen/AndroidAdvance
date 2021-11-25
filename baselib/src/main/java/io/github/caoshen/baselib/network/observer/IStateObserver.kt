package io.github.caoshen.baselib.network.observer

import androidx.lifecycle.Observer
import io.github.caoshen.baselib.network.entity.*

abstract class IStateObserver<T> : Observer<ApiResponse<T>> {

    override fun onChanged(apiResponse: ApiResponse<T>) {
        when (apiResponse) {
            is ApiSuccessResponse -> onSuccess(apiResponse.response)
            is ApiEmptyResponse -> onDataEmpty()
            is ApiFailedResponse -> onFailed(apiResponse.errorCode, apiResponse.errorMsg)
            is ApiErrorResponse -> onException(apiResponse.error)
            else -> {

            }
        }
        onComplete()
    }

    abstract fun onComplete()

    abstract fun onException(error: Throwable)

    abstract fun onFailed(errorCode: Int?, errorMsg: String?)

    abstract fun onDataEmpty()

    abstract fun onSuccess(data: T)


}