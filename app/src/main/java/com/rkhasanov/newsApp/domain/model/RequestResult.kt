package com.rkhasanov.newsApp.domain.model

class RequestResult {
    var status: String? = null
    var totalResults = 0
    var articles: List<Article>? = null
}