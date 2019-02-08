package com.ionnt.newscleanarchkotlin.repositories

import com.ionnt.newscleanarchkotlin.commons.exception.Failure
import com.ionnt.newscleanarchkotlin.commons.exception.Failure.ServerError
import com.ionnt.newscleanarchkotlin.commons.exception.Failure.NetworkConnection
import com.ionnt.newscleanarchkotlin.commons.utils.Either
import com.ionnt.newscleanarchkotlin.commons.utils.Either.Left
import com.ionnt.newscleanarchkotlin.commons.utils.Either.Right
import com.ionnt.newscleanarchkotlin.commons.utils.NetworkHandler
import com.ionnt.newscleanarchkotlin.dtos.NewsResponse
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.services.NewsService
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 29/01/2019.
 */
interface NewsRepository {
    fun headlineNews(country: String): Either<Failure, List<Articles>>
    fun newsByCategory(page: Int, pageSize: Int, category: String): Either<Failure, List<Articles>>

    class NewsRepositoryImpl @Inject constructor(private val networkHandler: NetworkHandler,
                                                 private val service: NewsService): NewsRepository {

        override fun headlineNews(country: String): Either<Failure, List<Articles>> {
            return when (networkHandler.isConnected) {
                true -> request(service.getTopHeadlines(country), { it.articles }, NewsResponse.empty())
                false, null -> Left(NetworkConnection)
            }
        }

        override fun newsByCategory(page: Int, pageSize: Int, category: String): Either<Failure, List<Articles>> {
            return when (networkHandler.isConnected) {
                true -> request(service.getNewsByCategory(category, page, pageSize), { it.articles }, NewsResponse.empty())
                false, null -> Left(NetworkConnection)
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError)
                }
            } catch (exception: Throwable) {
                Left(ServerError)
            }
        }
    }
}