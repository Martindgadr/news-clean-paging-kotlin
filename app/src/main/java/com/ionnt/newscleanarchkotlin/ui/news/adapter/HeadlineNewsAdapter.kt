package com.ionnt.newscleanarchkotlin.ui.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ionnt.newscleanarchkotlin.R
import com.ionnt.newscleanarchkotlin.commons.extensions.inflate
import com.ionnt.newscleanarchkotlin.model.Articles

/**
 * Created by Martin De Girolamo on 05/02/2019.
 */

class HeadlineNewsAdapter(private var list: List<Articles>): RecyclerView.Adapter<NewsByCategoryViewHolder>() {
    internal var onClickAction: ((Articles) -> Unit) = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsByCategoryViewHolder =
        NewsByCategoryViewHolder(parent.inflate(R.layout.news_by_category_item))

    override fun onBindViewHolder(holder: NewsByCategoryViewHolder, position: Int) =
        holder.bind(list[position], onClickAction)

    override fun getItemCount(): Int = list.size

    fun setListData(list: List<Articles>) {
        this.list = list
        notifyDataSetChanged()
    }
}