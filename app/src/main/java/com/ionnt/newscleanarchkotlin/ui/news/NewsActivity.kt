package com.ionnt.newscleanarchkotlin.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ionnt.newscleanarchkotlin.commons.base.BaseActivity
import com.ionnt.newscleanarchkotlin.commons.base.BaseFragment

class NewsActivity: BaseActivity() {
    companion object {
        fun intentInstance(context: Context) = Intent(context, NewsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun fragment(): BaseFragment = NewsFragment()
}
