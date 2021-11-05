package com.rkhasanov.newsApp.presentation.news.favorites

import androidx.lifecycle.*
import com.rkhasanov.newsApp.domain.useCases.favArticles.GetFavArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesListFragmentViewModel @Inject constructor(
    private val getFavArticlesUseCase: GetFavArticlesUseCase
) : ViewModel() {

    val articles = getFavArticlesUseCase.flow

    fun loadArticles() {
        viewModelScope.launch {
            getFavArticlesUseCase.loadArticles()
        }
    }

}