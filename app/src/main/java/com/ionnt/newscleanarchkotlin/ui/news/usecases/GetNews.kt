package com.ionnt.newscleanarchkotlin.ui.news.usecases

import com.ionnt.newscleanarchkotlin.commons.exception.Failure
import com.ionnt.newscleanarchkotlin.commons.usecase.UseCase
import com.ionnt.newscleanarchkotlin.commons.utils.Either
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.repositories.NewsRepository
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 29/01/2019.
 */

class GetNews @Inject constructor(private val newsRepository: NewsRepository): UseCase<List<Articles>, GetNews.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<Articles>> =
        newsRepository.headlineNews(params.country)

    data class Params(val country: String)
}