package com.rkhasanov.newsApp.presentation.news

import com.rkhasanov.newsApp.domain.model.Article

sealed interface ArticleState {

    object Loading : ArticleState

    data class Error(
        val message: String
    ) : ArticleState

    data class Success(
        val articles: List<Article>
    ) : ArticleState

}