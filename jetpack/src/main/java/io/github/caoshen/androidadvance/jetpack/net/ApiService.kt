package io.github.caoshen.androidadvance.jetpack.net

import io.github.caoshen.androidadvance.jetpack.bean.User
import io.github.caoshen.androidadvance.jetpack.bean.WxArticleBean
import io.github.caoshen.baselib.network.entity.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }

    @GET("wxarticle/chapters/json")
    suspend fun getWxArticle(): ApiResponse<List<WxArticleBean>>

    @GET("abc/chapters/json")
    suspend fun getWxArticleError(): ApiResponse<List<WxArticleBean>>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") userName: String?,
        @Field("password") password: String?
    ): ApiResponse<User?>
}