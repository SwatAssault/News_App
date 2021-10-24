package com.rkhasanov.newsApp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.model.pojo.newsRequest.NewsRequester

class ArticlesList: ViewModel() {

    private var articles = MutableLiveData<RequestResult>()

    private var newsRequester = NewsRequester()

    fun getArticles(): MutableLiveData<RequestResult> {
        return articles
    }

    fun fetch() {
        newsRequester.execute { response ->
            articles.postValue(response)
        }
    }

}