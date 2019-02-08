package com.ionnt.newscleanarchkotlin.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ionnt.newscleanarchkotlin.commons.base.BaseViewModel
import com.ionnt.newscleanarchkotlin.commons.exception.Failure
import com.ionnt.newscleanarchkotlin.datasource.NewsDataSource
import com.ionnt.newscleanarchkotlin.datasource.NewsDataSourceFactory
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.ui.news.usecases.GetNews
import com.ionnt.newscleanarchkotlin.ui.news.usecases.GetNewsByCategory
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

class NewsViewModel @Inject constructor(private val getNews: GetNews,
                                        private val getNewsByCategory: GetNewsByCategory): BaseViewModel() {

    var articles: MutableLiveData<List<Articles>> = MutableLiveData()
    var newsList: LiveData<PagedList<Articles>> = MutableLiveData()
    private var mediatorLiveData: MutableLiveData<String> = MutableLiveData()
    private val pageSize = 5

    init {
        newsList = Transformations.switchMap<String, PagedList<Articles>>(mediatorLiveData) {
                input -> createNewsByCategoryLD(input)
        }
    }

    fun getNewsHeadlinesArticles(country: String) = getNews(GetNews.Params(country)) {
        it.either(::handleFailure, ::handleNewsHeadlines) }

    fun handleNewsHeadlines(headlinesArticles: List<Articles>) {
        articles.value = headlinesArticles.map { it }
    }

    fun getNewsByCategory(category: String) {
        mediatorLiveData.postValue(category)
    }

    private fun createNewsByCategoryLD(category: String): LiveData<PagedList<Articles>> {
        val newsDataSourceFactory = NewsDataSourceFactory(getNewsByCategory, category)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()

        failure = Transformations.switchMap<NewsDataSource,
                Failure>(newsDataSourceFactory.newsDataSourceLiveData, NewsDataSource::failure) as MutableLiveData<Failure>

        return LivePagedListBuilder<Int, Articles>(newsDataSourceFactory, config).build()
    }

    override fun cancelRequest() = getNews.cancel()
}