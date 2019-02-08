package com.ionnt.newscleanarchkotlin.model

/**
 * Created by Martin De Girolamo on 28/01/2019.
 */

data class Articles(val source: Source, val title: String, val description: String, val urlToImage: String?,
                    val publishedAt: String, val content: String)
