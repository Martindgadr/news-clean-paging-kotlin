package com.ionnt.newscleanarchkotlin.commons.di.modules

import android.content.Context
import com.ionnt.newscleanarchkotlin.BuildConfig
import com.ionnt.newscleanarchkotlin.TMDBApplication
import com.ionnt.newscleanarchkotlin.commons.interceptors.AuthorizationHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

@Module
class ApplicationModule {
    @Provides @Singleton
    fun provideApplicationContext(application: TMDBApplication): Context = application

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        okHttpClientBuilder.addInterceptor(AuthorizationHeaderInterceptor())

        return okHttpClientBuilder.build()
    }
}