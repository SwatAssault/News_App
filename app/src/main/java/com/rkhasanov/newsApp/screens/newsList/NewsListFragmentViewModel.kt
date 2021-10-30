package com.rkhasanov.newsApp.screens.newsList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rkhasanov.newsApp.model.newsRequest.NewsRequester
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.model.pojo.RequestResult


class NewsListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var articles = MutableLiveData<List<Article>>()

    private var newsRequester = NewsRequester()

    fun fetchNews() {
        val list = listOf(
            "android",
            "microsoft",
            "airplane",
            "warming",
            "earth",
            "minecraft",
            "medicine",
            "eminem",
            "video",
            "concert"
        )

        newsRequester.getRandomNews(list.random()) {
            articles.postValue(it?.articles)
        }
    }

}
