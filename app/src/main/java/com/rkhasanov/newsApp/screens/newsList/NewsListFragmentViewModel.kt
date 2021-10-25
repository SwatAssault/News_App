package com.rkhasanov.newsApp.screens.newsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkhasanov.newsApp.model.newsRequest.NewsRequester
import com.rkhasanov.newsApp.model.pojo.RequestResult

class NewsListFragmentViewModel : ViewModel() {

    private var requestResult = MutableLiveData<RequestResult>()

    private var newsRequester = NewsRequester()

    fun getRequestResult(): MutableLiveData<RequestResult> {
        return requestResult
    }

    fun fetch(title: String = "Android") {
        val list = listOf("android", "microsoft", "airplane", "warming", "earth", "minecraft", "medicine", "eminem", "video", "concert")
        newsRequester.execute({ response ->
            requestResult.postValue(response)
        }, list.random())
    }

}
