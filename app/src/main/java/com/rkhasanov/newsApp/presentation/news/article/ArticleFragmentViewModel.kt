package com.rkhasanov.newsApp.presentation.news.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhasanov.newsApp.domain.model.Article
import com.rkhasanov.newsApp.domain.useCases.favArticles.AddToFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleFragmentViewModel @Inject constructor(
    private val addToFavoritesUseCase : AddToFavoritesUseCase
) : ViewModel() {

    val article = addToFavoritesUseCase.flow

    fun addToFavorites(article: Article) {
        viewModelScope.launch {
            addToFavoritesUseCase.addToFavorites(article)
        }
    }

}