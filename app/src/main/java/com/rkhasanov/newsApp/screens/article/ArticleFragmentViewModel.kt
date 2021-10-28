package com.rkhasanov.newsApp.screens.article

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.utils.DATABASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    fun addToFavorites(article: Article, onSuccess:() -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            DATABASE.addToFavorites(article) {
                onSuccess()
            }
        }
    }

}