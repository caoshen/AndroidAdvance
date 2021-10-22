package io.github.caoshen.baselib.network.entity

import com.google.gson.JsonParseException
import io.github.caoshen.baselib.network.utils.toast
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

enum class HttpError(val errorCode: Int, val errorMsg: String) {
    TOKEN_EXPIRE(3001, "token is expired"),
    PARAMS_ERROR(4003, "params is error")
    // ... more
}

internal fun handlingApiExceptions(errorCode: Int?, errorMsg: String?) = when (errorCode) {
    HttpError.TOKEN_EXPIRE.errorCode -> {
        toast(HttpError.TOKEN_EXPIRE.errorMsg)
    }
    HttpError.PARAMS_ERROR.errorCode -> {
        toast(HttpError.PARAMS_ERROR.errorMsg)
    }
    else -> errorMsg?.let {
        toast(it)
    }
}

internal fun handlingExceptions(e: Throwable) = when (e) {
    is HttpException -> {
        toast(e.message())
    }
    is CancellationException -> {
        toast(e.toString())
    }
    is SocketTimeoutException -> {
        toast(e.toString())
    }
    is JsonParseException -> {
        toast(e.toString())
    }
    else -> {
        toast(e.toString())
    }
}