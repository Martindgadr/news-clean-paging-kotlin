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

class GetNewsByCategory @Inject constructor(private val newsRepository: NewsRepository): UseCase<List<Articles>,
        GetNewsByCategory.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<Articles>> =
        newsRepository.newsByCategory(params.page, params.pageSize, params.category)

    data class Params(val page: Int, val pageSize: Int, val category: String)
}