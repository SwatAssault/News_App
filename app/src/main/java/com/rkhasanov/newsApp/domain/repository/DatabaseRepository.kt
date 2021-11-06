package com.rkhasanov.newsApp.domain.repository

import com.rkhasanov.newsApp.domain.model.Article
import com.rkhasanov.newsApp.presentation.BasicRequestState
import com.rkhasanov.newsApp.presentation.news.ArticleState
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getFavoriteArticles(): Flow<ArticleState>

    fun addToFavorites(article: Article) : Flow<BasicRequestState>

}