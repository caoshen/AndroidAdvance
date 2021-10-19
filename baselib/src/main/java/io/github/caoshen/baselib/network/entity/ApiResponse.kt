package io.github.caoshen.baselib.network.entity

import java.io.Serializable

open class ApiResponse<T>(
    open val data: T? = null,
    open val errorCode: Int? = null,
    open val errorMsg: String? = null,
    open val error: Throwable? = null
) : Serializable {
    val isSuccess: Boolean
        get() = errorCode == 0

}