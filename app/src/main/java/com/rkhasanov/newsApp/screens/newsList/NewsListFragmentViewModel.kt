package com.rkhasanov.newsApp.screens.newsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhasanov.newsApp.model.newsRequest.NewsRequester
import com.rkhasanov.newsApp.model.pojo.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsListFragmentViewModel @Inject constructor(
    private val newsRequester: NewsRequester
): ViewModel() {

    var articles = MutableLiveData<List<Article>>()

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

        viewModelScope.launch(Dispatchers.IO) {
            newsRequester.getRandomNews(list.random()) {
                articles.postValue(it?.articles)
            }
        }
    }

}
