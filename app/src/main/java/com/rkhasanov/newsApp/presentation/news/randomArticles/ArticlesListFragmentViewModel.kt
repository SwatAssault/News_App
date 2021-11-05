package com.rkhasanov.newsApp.presentation.news.randomArticles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhasanov.newsApp.domain.useCases.randomArticles.GetRandomArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesListFragmentViewModel @Inject constructor(
    private val getRandomArticlesUseCase: GetRandomArticlesUseCase
) : ViewModel() {

    val articles = getRandomArticlesUseCase.flow

    fun getRandomArticles() {
        viewModelScope.launch {
            getRandomArticlesUseCase.getRandomArticles()
        }
    }


}
