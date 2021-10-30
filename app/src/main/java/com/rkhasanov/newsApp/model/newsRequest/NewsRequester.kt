package com.rkhasanov.newsApp.model.newsRequest

import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.utils.NEWS_API_BASE_URL
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsRequester {

    fun getRandomNews(title: String, onSuccess: (response: RequestResult?) -> Unit) {

        val newsApi = Retrofit.Builder()
            .baseUrl(NEWS_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = newsApi.getNews(title)
            if (response.isSuccessful) {
                onSuccess(response.body())
            }
        }
    }

}