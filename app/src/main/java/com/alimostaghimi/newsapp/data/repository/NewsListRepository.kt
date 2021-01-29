package com.alimostaghimi.newsapp.data.repository

import com.alimostaghimi.newsapp.data.model.News

interface NewsListRepository {
    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>
}