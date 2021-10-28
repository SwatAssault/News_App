package com.rkhasanov.newsApp.model.database.room

import androidx.room.*
import com.rkhasanov.newsApp.model.pojo.Article


@Dao
interface RoomDAO {
    @Query("SELECT * FROM favorite_articles")
    fun getFavoriteArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}