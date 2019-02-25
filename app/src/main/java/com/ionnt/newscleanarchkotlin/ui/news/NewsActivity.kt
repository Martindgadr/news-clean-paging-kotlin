package com.ionnt.newscleanarchkotlin.ui.news

import android.content.Context
import android.content.Intent
import com.ionnt.newscleanarchkotlin.commons.base.BaseActivity
import com.ionnt.newscleanarchkotlin.commons.base.BaseFragment

class NewsActivity: BaseActivity() {
    companion object {
        fun intentInstance(context: Context) = Intent(context, NewsActivity::class.java)
    }

    override fun fragment(): BaseFragment = NewsFragment()
}
