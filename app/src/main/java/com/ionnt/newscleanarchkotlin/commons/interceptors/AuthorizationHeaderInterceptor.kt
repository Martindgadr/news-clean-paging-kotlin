package com.ionnt.newscleanarchkotlin.commons.interceptors

import com.ionnt.newscleanarchkotlin.commons.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Martin De Girolamo on 28/01/2019.
 */

class AuthorizationHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("X-Api-Key", Constants.AUTH_HEADER_API_KEY)
            .build()

        return chain.proceed(request)
    }
}