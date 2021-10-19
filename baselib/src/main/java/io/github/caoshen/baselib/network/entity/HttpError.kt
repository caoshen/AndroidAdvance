package io.github.caoshen.baselib.network.entity

enum class HttpError(val errorCode: Int?, val errorMsg: String?) {
    TOKEN_EXPIRE(3001, "token is expired"),
    PARAMS_ERROR(4003, "params is error")
    // ... more
}

internal fun handlingApiExceptions(errorCode: Int?, errorMsg: String?) = when (errorCode) {
    HttpError.TOKEN_EXPIRE.errorCode -> {

    }
    else -> errorMsg?.let {

    }
}