package io.github.caoshen.androidadvance.jetpack.retrofit.network.interceptor

import io.github.caoshen.androidadvance.jetpack.retrofit.network.exception.ApiException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.nio.charset.StandardCharsets

/**
 * @author caoshen
 * @date 2021/1/27
 */
class BusinessErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            return response
        }

        val responseBody = response.body ?: return response

        val source = responseBody.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        val charset = responseBody.contentType()?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        val resultString = buffer.clone().readString(charset)

        val jsonObject = JSONObject(resultString)
        if (!jsonObject.has("errorCode")) {
            return response
        } else {
            val code = jsonObject.optInt("errorCode")
            if (code == 0) {
                return response
            } else {
                // business error. e.g. illegal arguments
                throw ApiException(code, "some error msg:$code")
            }
        }
    }
}