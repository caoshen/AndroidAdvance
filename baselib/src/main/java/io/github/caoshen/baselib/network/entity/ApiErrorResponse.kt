package io.github.caoshen.baselib.network.entity

data class ApiErrorResponse<T>(override val error: Throwable?) :
    ApiResponse<T>(error = error)