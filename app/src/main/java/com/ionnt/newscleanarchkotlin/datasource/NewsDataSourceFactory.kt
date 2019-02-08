package com.ionnt.newscleanarchkotlin.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.ui.news.usecases.GetNewsByCategory

/**
 * Created by Martin De Girolamo on 31/01/2019.
 */
class NewsDataSourceFactory(private val getNewsByCategory: GetNewsByCategory,
                            private val category: String): DataSource.Factory<Int, Articles>() {
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, Articles> {
        val newsDataSource = NewsDataSource(getNewsByCategory, category)
        newsDataSourceLiveData.postValue(newsDataSource)

        return newsDataSource
    }
}