package com.rkhasanov.newsApp.model.pojo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "favorite_articles")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String
) : Serializable {

    @Ignore
    var source: Source? = null
    @Ignore
    var publishedAt: Date? = null
}