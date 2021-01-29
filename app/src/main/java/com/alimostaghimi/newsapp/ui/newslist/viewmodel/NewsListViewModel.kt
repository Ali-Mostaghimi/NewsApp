package com.alimostaghimi.newsapp.ui.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alimostaghimi.newsapp.data.model.News
import com.alimostaghimi.newsapp.data.repository.NewsListRepository
import com.alimostaghimi.newsapp.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsListViewModel(private val newsListRepository: NewsListRepository) : ViewModel() {
    private val newsList: MutableLiveData<Resource<List<News>>> = MutableLiveData()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsList.postValue(Resource.loading(null))
            try {
                val response = newsListRepository.getNews()
                newsList.postValue(Resource.success(response))
            } catch (e: Exception) {
                newsList.postValue(Resource.error(null, e.toString()))
            }
        }
    }

    fun getNews(): LiveData<Resource<List<News>>> = newsList
}