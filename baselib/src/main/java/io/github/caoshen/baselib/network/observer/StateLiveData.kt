package io.github.caoshen.baselib.network.observer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import io.github.caoshen.baselib.network.entity.ApiResponse

class StateLiveData<T> : MutableLiveData<ApiResponse<T>>() {

    fun observeState(owner: LifecycleOwner, listenerBuilder: ListenerBuilder.() -> Unit) {
        val listener = ListenerBuilder().also(listenerBuilder)
        val value = object  : IStateObserver<T>() {
            override fun onComplete() {
                listener.mCompleteListenerAction?.invoke()
            }

            override fun onError(error: Throwable) {
                listener.mErrorListenerAction?.invoke(error)
            }

            override fun onFailed(errorCode: Int?, errorMsg: String?) {
                listener.mFailedListenerAction?.invoke(errorCode, errorMsg)
            }

            override fun onDataEmpty() {
                listener.mEmptyListenerAction?.invoke()
            }

            override fun onSuccess(data: T) {
                listener.mSuccessListenerAction?.invoke(data)
            }
        }

        super.observe(owner, value)
    }

    inner class ListenerBuilder {
        internal var mSuccessListenerAction: ((T) -> Unit)? = null

        internal var mFailedListenerAction: ((Int?, String?) -> Unit)? = null

        internal var mErrorListenerAction: ((Throwable) -> Unit)? = null

        internal var mEmptyListenerAction: (() -> Unit)? = null

        internal var mCompleteListenerAction: (() -> Unit)? = null

        fun onSuccess(action: (T) -> Unit) {
            mSuccessListenerAction = action
        }

        fun onFailed(action: (Int?, String?) -> Unit) {
            mFailedListenerAction = action
        }

        fun onError(action: (Throwable) -> Unit) {
            mErrorListenerAction = action
        }

        fun onEmpty(action: () -> Unit) {
            mEmptyListenerAction = action
        }

        fun onComplete(action: () -> Unit) {
            mCompleteListenerAction = action
        }
    }

}