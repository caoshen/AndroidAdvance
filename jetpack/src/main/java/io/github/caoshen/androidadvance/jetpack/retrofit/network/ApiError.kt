package io.github.caoshen.androidadvance.jetpack.retrofit.network

/**
 * @author caoshen
 * @date 2021/1/27
 */
object ApiError {
    val dataIsNull = Error(-1, "data is null")

    val httpStatusCodeError = Error(-2, "Server error. please try again later")

    val unknownException = Error(-3, "unknown exception")
}

data class Error(val errorCode: Int, val errorMsg: String)