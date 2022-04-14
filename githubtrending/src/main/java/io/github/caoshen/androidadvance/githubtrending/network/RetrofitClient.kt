package io.github.caoshen.androidadvance.githubtrending.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.caoshen.androidadvance.githubtrending.network.moshi.NullStringAdapter
import io.github.caoshen.androidadvance.githubtrending.network.service.RepoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val TAG = "RetrofitClient"
    private const val TIME_OUT = 10
    private const val BASE_URL = "https://trendings.herokuapp.com/"


    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .build()
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
                .add(NullStringAdapter)
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    val service: RepoService by lazy {
        getService(RepoService::class.java, BASE_URL)
    }

    private fun <S> getService(
            serviceClass: Class<S>,
            baseUrl: String,
            client: OkHttpClient = this.client
    ): S {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(serviceClass)
    }
}