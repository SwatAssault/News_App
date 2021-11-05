package com.rkhasanov.newsApp.domain.useCases.favArticles

import com.rkhasanov.newsApp.domain.repository.DatabaseRepository
import com.rkhasanov.newsApp.presentation.news.ArticleState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavArticlesUseCase @Inject constructor(
    private val repository: DatabaseRepository
) {

    private val _flow = MutableStateFlow<ArticleState>(ArticleState.Loading)
    val flow = _flow.asStateFlow()

    suspend fun loadArticles() = withContext(Dispatchers.IO) {
        repository.getFavoriteArticles().onEach {
            _flow.emit(it)
        }.launchIn(this)
    }

}