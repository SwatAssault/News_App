package com.rkhasanov.newsApp.data.dataSource.net

import com.rkhasanov.newsApp.domain.model.RequestResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers(
        "X-Api-Key:$NEWS_API_TOKEN",
    )
    @GET("/v2/everything")
    suspend fun getArticles(@Query("qInTitle") keyWord: String): RequestResult

}