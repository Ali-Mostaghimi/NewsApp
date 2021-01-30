package com.alimostaghimi.newsapp.ui.newslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alimostaghimi.newsapp.R
import com.alimostaghimi.newsapp.data.api.ApiHelperImpl
import com.alimostaghimi.newsapp.data.api.RetrofitBuilder
import com.alimostaghimi.newsapp.data.model.News
import com.alimostaghimi.newsapp.ui.base.ViewModelFactory
import com.alimostaghimi.newsapp.ui.newslist.adapter.NewsListAdapter
import com.alimostaghimi.newsapp.ui.newslist.viewmodel.NewsListViewModel
import com.alimostaghimi.newsapp.ui.newslist.viewmodel.ParallelNewsListViewModel
import com.alimostaghimi.newsapp.ui.newslist.viewmodel.SeriesNewsListViewModel
import com.alimostaghimi.newsapp.utils.Status
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {
    private lateinit var newsListViewModel: ParallelNewsListViewModel
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        newsListViewModel.getNews().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS ->{
                    progressBar.visibility = View.GONE
                    it.data?.let {newsList ->
                        renderList(newsList)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING->{
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR-> {
                    //handle error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(newsList: List<News>) {
        adapter.addData(newsList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        newsListViewModel = ViewModelProvider(
                this,
                ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(ParallelNewsListViewModel::class.java)
    }
}