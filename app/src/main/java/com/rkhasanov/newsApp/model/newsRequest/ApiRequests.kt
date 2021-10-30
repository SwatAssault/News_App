package com.rkhasanov.newsApp.model.newsRequest

import com.rkhasanov.newsApp.model.pojo.RequestResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers(
        "X-Api-Key: e1b919c8ae3d48658095b4fed9091816",
    )
    @GET("/v2/everything")
    suspend fun getNews(@Query("qInTitle") keyWord: String): Response<RequestResult>

}