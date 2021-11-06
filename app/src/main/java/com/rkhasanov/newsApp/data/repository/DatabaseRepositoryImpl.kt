package com.rkhasanov.newsApp.data.repository

import com.rkhasanov.newsApp.data.dataSource.database.RoomDAO
import com.rkhasanov.newsApp.domain.model.Article
import com.rkhasanov.newsApp.domain.repository.DatabaseRepository
import com.rkhasanov.newsApp.presentation.BasicRequestState
import com.rkhasanov.newsApp.presentation.news.ArticleState
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class DatabaseRepositoryImpl (
    private val dao: RoomDAO
) : DatabaseRepository {

    override fun getFavoriteArticles() = flow<ArticleState> {
        emit(ArticleState.Loading)
        try {
            val data = dao.getFavoriteArticles()
            emit(ArticleState.Success(data))
        } catch (e: Exception) {
            emit(ArticleState.Error(e.message ?: "Error occurred while executing request"))
        }
    }

    override fun addToFavorites(article: Article) = flow<BasicRequestState> {
        emit(BasicRequestState.Loading)
        try {
            dao.insert(article)
            emit(BasicRequestState.Success)
        } catch (e: Exception) {
            emit(BasicRequestState.Error(e.message ?: "Error occurred while executing request"))
        }
    }

}