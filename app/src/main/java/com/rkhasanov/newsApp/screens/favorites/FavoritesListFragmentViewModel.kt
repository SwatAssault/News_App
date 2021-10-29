package com.rkhasanov.newsApp.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.utils.DATABASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoritesListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var articles = MutableLiveData<List<Article>>()

    fun getArticles(): MutableLiveData<List<Article>> {
        return articles
    }

    fun fetchFavoriteArticles(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            DATABASE.fetchFavoriteArticles() {
                articles.postValue(it)
                onSuccess()
            }
        }
    }

}