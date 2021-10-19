package io.github.caoshen.baselib.network.entity

data class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(data = response)