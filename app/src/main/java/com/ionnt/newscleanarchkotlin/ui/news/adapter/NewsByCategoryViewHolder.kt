package com.ionnt.newscleanarchkotlin.ui.news.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ionnt.newscleanarchkotlin.commons.extensions.loadFromUrl
import com.ionnt.newscleanarchkotlin.model.Articles
import kotlinx.android.synthetic.main.news_by_category_item.view.*

/**
 * Created by Martin De Girolamo on 01/02/2019.
 */

class NewsByCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(article: Articles?, clickListener: (Articles) -> Unit) {
        article?.let { item ->
            item.urlToImage?.let { itemView.newsImageView.loadFromUrl(it) }
            itemView.newsTitleTextView.text = item.title
            itemView.setOnClickListener { clickListener(item) }
        }
    }
}