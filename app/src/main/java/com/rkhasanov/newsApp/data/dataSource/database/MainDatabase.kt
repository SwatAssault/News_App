package com.rkhasanov.newsApp.data.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rkhasanov.newsApp.domain.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract val roomDAO: RoomDAO

    companion object {
        const val DATABASE_NAME = "database"
    }

}