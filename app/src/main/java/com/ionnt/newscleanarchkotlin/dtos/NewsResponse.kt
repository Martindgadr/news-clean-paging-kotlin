package com.ionnt.newscleanarchkotlin.dtos

import com.ionnt.newscleanarchkotlin.model.Articles

/**
 * Created by Martin De Girolamo on 28/01/2019.
 */

data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Articles>){
    companion object {
        fun empty() = NewsResponse("", 0, emptyList())
    }
}