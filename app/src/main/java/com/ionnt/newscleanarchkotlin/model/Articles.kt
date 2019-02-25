package com.ionnt.newscleanarchkotlin.model

import java.io.Serializable

/**
 * Created by Martin De Girolamo on 28/01/2019.
 */

data class Articles(val source: Source, val author: String, val title: String, val description: String,
                    val urlToImage: String?, val publishedAt: String, val content: String, val url: String): Serializable
