package com.ionnt.newscleanarchkotlin.navigations

import android.content.Context
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.ui.news.NewsActivity
import com.ionnt.newscleanarchkotlin.ui.newsdetail.NewsDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 30/01/2019.
 */

@Singleton
class Navigator @Inject constructor() {
    fun showMainNews(context: Context) = context.startActivity(NewsActivity.intentInstance(context))
    fun showNewsDetail(context: Context, articles: Articles) =
        context.startActivity(NewsDetailActivity.intentInstance(context, articles))
}