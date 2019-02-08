package com.ionnt.newscleanarchkotlin.commons.utils

import android.content.Context
import com.ionnt.newscleanarchkotlin.commons.extensions.networkState
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 24/01/2019.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkState?.isConnected
}