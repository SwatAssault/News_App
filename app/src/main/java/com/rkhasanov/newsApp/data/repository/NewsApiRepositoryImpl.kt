package com.rkhasanov.newsApp.data.repository

import com.rkhasanov.newsApp.data.dataSource.net.NewsApi
import com.rkhasanov.newsApp.domain.repository.NewsApiRepository
import com.rkhasanov.newsApp.presentation.news.ArticleState
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NewsApiRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsApiRepository {

    override fun getRandomArticles(keyword: String) = flow<ArticleState> {
        emit(ArticleState.Loading)
        try {
            val data = newsApi.getArticles(keyword)
            emit(ArticleState.Success(data.articles ?: emptyList()))
        } catch (e: Exception) {
            emit(ArticleState.Error(e.message ?: "Error"))
        }
    }

}