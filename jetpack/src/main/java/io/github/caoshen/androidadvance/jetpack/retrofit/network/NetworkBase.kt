package io.github.caoshen.androidadvance.jetpack.retrofit.network

import io.github.caoshen.androidadvance.jetpack.retrofit.ApiResultCallAdapterFactory
import io.github.caoshen.androidadvance.jetpack.retrofit.network.interceptor.BusinessErrorInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author caoshen
 * @date 2021/1/21
 */
private const val BASE_URL = "http://fanyi.youdao.com/"

private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(BusinessErrorInterceptor())
        .build()

val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ApiResultCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()