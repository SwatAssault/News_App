package com.rkhasanov.newsApp.model.newsRequest

import com.rkhasanov.newsApp.model.pojo.RequestResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiRequests {

    @Headers(
        "X-Api-Key: e1b919c8ae3d48658095b4fed9091816",
    )
    @GET("/v2/everything?qInTitle=android")
    fun getNews(): Call<RequestResult>

}