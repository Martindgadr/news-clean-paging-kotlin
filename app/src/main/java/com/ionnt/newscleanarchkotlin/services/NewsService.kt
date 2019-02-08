package com.ionnt.newscleanarchkotlin.services

import com.ionnt.newscleanarchkotlin.dtos.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 29/01/2019.
 */

@Singleton
class NewsService @Inject constructor(retrofit: Retrofit): NewsAPI {
    private val newsApi by lazy { retrofit.create(NewsAPI::class.java) }

    override fun getTopHeadlines(country: String): Call<NewsResponse> = newsApi.getTopHeadlines(country)
    override fun getNewsByCategory(category: String, page: Int, pageSize: Int): Call<NewsResponse>
            = newsApi.getNewsByCategory(category, page, pageSize)
}