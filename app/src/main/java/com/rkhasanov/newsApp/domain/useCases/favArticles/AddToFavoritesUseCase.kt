package com.rkhasanov.newsApp.domain.useCases.favArticles

import com.rkhasanov.newsApp.domain.model.Article
import com.rkhasanov.newsApp.domain.repository.DatabaseRepository
import com.rkhasanov.newsApp.presentation.BasicRequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: DatabaseRepository
) {

    private val _flow = MutableStateFlow<BasicRequestState>(BasicRequestState.Loading)
    val flow = _flow.asStateFlow()

    suspend fun addToFavorites(article: Article) = withContext(Dispatchers.IO) {
        repository.addToFavorites(article).onEach {
            _flow.emit(it)
        }.launchIn(this)
    }

}