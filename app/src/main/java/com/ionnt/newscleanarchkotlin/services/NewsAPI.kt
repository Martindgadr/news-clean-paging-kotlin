package com.ionnt.newscleanarchkotlin.services

import com.ionnt.newscleanarchkotlin.dtos.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Martin De Girolamo on 28/01/2019.
 */

internal interface NewsAPI {
    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("country") country: String): Call<NewsResponse>

    @GET("/v2/everything")
    fun getNewsByCategory(@Query("q") category: String, @Query("page") page: Int,
                          @Query("pageSize") pageSize: Int): Call<NewsResponse>
}