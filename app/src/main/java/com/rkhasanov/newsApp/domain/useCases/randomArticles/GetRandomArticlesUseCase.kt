package com.rkhasanov.newsApp.domain.useCases.randomArticles

import com.rkhasanov.newsApp.data.repository.NewsApiRepositoryImpl
import com.rkhasanov.newsApp.presentation.news.ArticleState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRandomArticlesUseCase @Inject constructor(
    private val repository: NewsApiRepositoryImpl
) {

    private val _flow = MutableStateFlow<ArticleState>(ArticleState.Loading)
    val flow = _flow.asStateFlow()

    private val list = listOf(
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

    suspend fun getRandomArticles() = withContext(Dispatchers.IO) {
        repository.getRandomArticles(list.random()).onEach {
            _flow.emit(it)
        }.launchIn(this)
    }

}