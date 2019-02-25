package com.ionnt.newscleanarchkotlin.ui.newsdetail

import android.os.Bundle
import android.view.View
import com.ionnt.newscleanarchkotlin.R
import com.ionnt.newscleanarchkotlin.commons.base.BaseFragment
import com.ionnt.newscleanarchkotlin.commons.extensions.loadFromUrl
import com.ionnt.newscleanarchkotlin.model.Articles
import kotlinx.android.synthetic.main.fragment_news_detail.*

/**
 * Created by Martin De Girolamo on 08/02/2019.
 */
class NewsDetailFragment: BaseFragment() {
    private lateinit var articles: Articles

    override fun layoutId(): Int = R.layout.fragment_news_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        articles = requireActivity().intent?.extras?.getSerializable(NewsDetailActivity.intentExtra) as Articles
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleText.text = articles.title
        descriptionText.text = articles.content
        articles.urlToImage?.let { newsImageView.loadFromUrl(it) }
        authorName.text = articles.author
        linkWeb.text = articles.url
    }
}