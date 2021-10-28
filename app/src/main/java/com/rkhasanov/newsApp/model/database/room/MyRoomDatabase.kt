package com.rkhasanov.newsApp.model.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rkhasanov.newsApp.model.pojo.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getRoomDAO(): RoomDAO

    companion object {
        @Volatile
        private var database: MainDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MainDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    MainDatabase::class.java,
                    "database"
                ).build()
                database as MainDatabase
            } else {
                database as MainDatabase
            }
        }
    }

}