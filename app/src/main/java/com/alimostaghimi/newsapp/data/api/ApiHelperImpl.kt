package com.alimostaghimi.newsapp.data.api

import com.alimostaghimi.newsapp.data.model.News

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNews(): List<News> = apiService.getNews()

    override suspend fun getMoreNews(): List<News> = apiService.getMoreNews()
}