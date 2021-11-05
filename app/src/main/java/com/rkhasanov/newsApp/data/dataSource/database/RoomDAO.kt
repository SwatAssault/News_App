package com.rkhasanov.newsApp.data.dataSource.database

import androidx.room.*
import com.rkhasanov.newsApp.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDAO {
    @Query("SELECT * FROM favorite_articles")
    fun getFavoriteArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}