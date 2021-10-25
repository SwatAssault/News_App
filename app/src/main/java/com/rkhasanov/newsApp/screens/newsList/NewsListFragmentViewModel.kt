package com.rkhasanov.newsApp.screens.newsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkhasanov.newsApp.model.newsRequest.NewsRequester
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.model.pojo.RequestResult

class NewsListFragmentViewModel : ViewModel() {

    private var articles = MutableLiveData<List<Article>>()

    private var newsRequester = NewsRequester()

    fun getArticles(): MutableLiveData<List<Article>> {
        return articles
    }

    fun fetch(title: String = "Android") {
        newsRequester.execute({ response ->
            articles.postValue(response?.articles!!)
        }, title)
    }

}
