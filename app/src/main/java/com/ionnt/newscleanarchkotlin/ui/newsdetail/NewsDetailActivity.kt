package com.ionnt.newscleanarchkotlin.ui.newsdetail

import android.content.Context
import android.content.Intent
import com.ionnt.newscleanarchkotlin.commons.base.BaseActivity
import com.ionnt.newscleanarchkotlin.commons.base.BaseFragment
import com.ionnt.newscleanarchkotlin.model.Articles

/**
 * Created by Martin De Girolamo on 08/02/2019.
 */
class NewsDetailActivity: BaseActivity() {

    companion object {
        val intentExtra = "intentArticle"

        fun intentInstance(context: Context, articles: Articles): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(intentExtra, articles)
            return intent
        }
    }

    override fun fragment(): BaseFragment = NewsDetailFragment()
}