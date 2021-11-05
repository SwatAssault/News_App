package com.rkhasanov.newsApp.di

import android.app.Application
import androidx.room.Room
import com.rkhasanov.newsApp.data.dataSource.database.MainDatabase
import com.rkhasanov.newsApp.domain.repository.DatabaseRepository
import com.rkhasanov.newsApp.data.repository.DatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMainDatabase(application: Application): MainDatabase {
        return Room.databaseBuilder(
            application,
            MainDatabase::class.java,
            MainDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(database: MainDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(database.roomDAO)
    }

}