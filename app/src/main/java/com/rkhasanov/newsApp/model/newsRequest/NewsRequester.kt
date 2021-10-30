package com.rkhasanov.newsApp.model.newsRequest

import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.utils.NEWS_API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class NewsRequester @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun getRandomNews(title: String, onSuccess: (response: RequestResult?) -> Unit) {
        val response = newsApi.getNews(title)
        if (response.isSuccessful) {
            onSuccess(response.body())
        }
    }

}