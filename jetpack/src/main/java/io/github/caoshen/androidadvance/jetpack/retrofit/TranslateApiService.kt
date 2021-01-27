package io.github.caoshen.androidadvance.jetpack.retrofit

import io.github.caoshen.androidadvance.jetpack.retrofit.network.ApiResult
import io.github.caoshen.androidadvance.jetpack.retrofit.network.retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author caoshen
 * @date 2021/1/27
 */

/**
 * http://fanyi.youdao.com/translate?doctype=json&i=Hello%20world
 */
interface TranslateApiService {
    @FormUrlEncoded
    @POST("translate?doctype=json")
    suspend fun translate(@Field("i") i: String): ApiResult<Result>
}

class Result(val type: String, val elapsedTime: Int, val translateResult: List<List<TranslateResult>>) {

    data class TranslateResult(val src: String, val tgt: String)
}

/**
 * singleton, lazy mode
 */
object TranslateApi {
    val retrofitService: TranslateApiService by lazy {
        retrofit.create(TranslateApiService::class.java)
    }
}