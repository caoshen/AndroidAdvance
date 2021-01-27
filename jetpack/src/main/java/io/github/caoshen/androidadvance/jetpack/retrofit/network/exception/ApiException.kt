package io.github.caoshen.androidadvance.jetpack.retrofit.network.exception

import java.io.IOException

/**
 * @author caoshen
 * @date 2021/1/27
 */
class ApiException(val errorCode: Int, val errorMsg: String) : IOException()