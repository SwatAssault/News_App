package com.rkhasanov.newsApp.model.database.room

import androidx.lifecycle.LiveData
import com.rkhasanov.newsApp.model.pojo.Article


class DatabaseRepo(private val dao: RoomDAO) {

    fun fetchFavoriteArticles(onSuccess: (articles: List<Article>) -> Unit) {
        onSuccess(dao.getFavoriteArticles())
    }

    suspend fun addToFavorites(article: Article, onSuccess: () -> Unit) {
        dao.insert(article)
        onSuccess()
    }

}