package com.rkhasanov.newsApp.model.di

import com.rkhasanov.newsApp.model.newsRequest.NewsApi
import com.rkhasanov.newsApp.model.newsRequest.NewsRequester
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
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder().baseUrl(NEWS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRequester(newsApi: NewsApi) : NewsRequester {
        return NewsRequester(newsApi)
    }

}