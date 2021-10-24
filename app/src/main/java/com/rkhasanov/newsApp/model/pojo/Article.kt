package com.rkhasanov.newsApp.model.pojo

import java.util.*

class Article {
    var source: Source? = null
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: Date? = null
    var content: String? = null
}