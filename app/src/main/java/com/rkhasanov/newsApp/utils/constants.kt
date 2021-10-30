package com.rkhasanov.newsApp.utils

import com.rkhasanov.newsApp.model.database.room.DatabaseRepo
import com.rkhasanov.newsApp.view.MainActivity


const val NEWS_API_BASE_URL = "https://newsapi.org"
lateinit var APP_CONTEXT: MainActivity
lateinit var DATABASE: DatabaseRepo