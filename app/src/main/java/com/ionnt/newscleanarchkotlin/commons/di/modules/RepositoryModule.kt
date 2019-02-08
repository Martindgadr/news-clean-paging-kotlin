package com.ionnt.newscleanarchkotlin.commons.di.modules

import com.ionnt.newscleanarchkotlin.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 29/01/2019.
 */

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesNewsRepository(sourceRepository: NewsRepository.NewsRepositoryImpl): NewsRepository = sourceRepository
}