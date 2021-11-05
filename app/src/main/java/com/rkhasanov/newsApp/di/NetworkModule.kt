package com.rkhasanov.newsApp.di

import com.rkhasanov.newsApp.data.dataSource.net.NewsApi
import com.rkhasanov.newsApp.data.repository.NewsApiRepositoryImpl
import com.rkhasanov.newsApp.utils.NEWS_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(NEWS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsRequester(newsApi: NewsApi): NewsApiRepositoryImpl {
        return NewsApiRepositoryImpl(newsApi)
    }

}