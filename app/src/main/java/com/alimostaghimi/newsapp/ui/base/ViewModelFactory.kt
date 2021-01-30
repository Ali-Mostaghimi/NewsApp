package com.alimostaghimi.newsapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alimostaghimi.newsapp.data.api.ApiHelper
import com.alimostaghimi.newsapp.data.repository.NewsListRepositoryImpl
import com.alimostaghimi.newsapp.ui.newslist.viewmodel.NewsListViewModel
import com.alimostaghimi.newsapp.ui.newslist.viewmodel.ParallelNewsListViewModel
import com.alimostaghimi.newsapp.ui.newslist.viewmodel.SeriesNewsListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsListViewModel::class.java)){
            return NewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        if(modelClass.isAssignableFrom(SeriesNewsListViewModel::class.java)){
            return SeriesNewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        if(modelClass.isAssignableFrom(ParallelNewsListViewModel::class.java)){
            return ParallelNewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}