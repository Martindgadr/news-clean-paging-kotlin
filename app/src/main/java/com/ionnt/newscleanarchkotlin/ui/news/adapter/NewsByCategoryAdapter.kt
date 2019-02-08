package com.ionnt.newscleanarchkotlin.ui.news.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ionnt.newscleanarchkotlin.R
import com.ionnt.newscleanarchkotlin.commons.extensions.inflate
import com.ionnt.newscleanarchkotlin.model.Articles

/**
 * Created by Martin De Girolamo on 01/02/2019.
 */

class NewsByCategoryAdapter: PagedListAdapter<Articles, NewsByCategoryViewHolder>(NewsDiffCallback) {
    internal var onClickAction: ((Articles) -> Unit) = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsByCategoryViewHolder =
        NewsByCategoryViewHolder(parent.inflate(R.layout.news_by_category_item))

    override fun onBindViewHolder(holder: NewsByCategoryViewHolder, position: Int) =
        holder.bind(getItem(position), onClickAction)

    override fun getItemCount(): Int {
        return super.getItemCount()  + 0
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem == newItem
            }
        }
    }
}