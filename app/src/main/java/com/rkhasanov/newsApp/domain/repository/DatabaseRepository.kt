package com.rkhasanov.newsApp.domain.repository

import com.rkhasanov.newsApp.presentation.news.ArticleState
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getFavoriteArticles(): Flow<ArticleState>

}