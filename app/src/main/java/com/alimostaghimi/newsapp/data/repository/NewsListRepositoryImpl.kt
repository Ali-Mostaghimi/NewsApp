package com.alimostaghimi.newsapp.data.repository

import com.alimostaghimi.newsapp.data.api.ApiHelper
import com.alimostaghimi.newsapp.data.model.News

class NewsListRepositoryImpl(private val apiHelper: ApiHelper) : NewsListRepository {
    override suspend fun getNews() = apiHelper.getNews()

    override suspend fun getMoreNews() = apiHelper.getMoreNews()
}