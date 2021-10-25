package com.rkhasanov.newsApp.model.newsRequest

import com.google.gson.Gson
import com.rkhasanov.newsApp.model.pojo.RequestResult
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import java.io.IOException

class NewsRequester {

    fun execute(title: String, onResult: (response: RequestResult?) -> Unit) {
        val url = "https://newsapi.org/v2/everything?qInTitle=$title"
        val client = OkHttpClient()

        val map = mapOf("X-Api-Key" to "e1b919c8ae3d48658095b4fed9091816")
        val headers: Headers = map.toHeaders()

        val request = Request.Builder().url(url).headers(headers).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (response.isSuccessful) {
                        onResult(Gson().fromJson(response.body!!.string(), RequestResult::class.java))
                    } else {
                        onResult(null)
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }


}