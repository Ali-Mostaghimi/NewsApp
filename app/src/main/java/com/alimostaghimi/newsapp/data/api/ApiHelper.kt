package com.alimostaghimi.newsapp.data.api

import com.alimostaghimi.newsapp.data.model.News

interface ApiHelper {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>
}