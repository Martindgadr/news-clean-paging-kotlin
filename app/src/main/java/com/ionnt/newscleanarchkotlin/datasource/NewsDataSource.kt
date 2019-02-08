package com.ionnt.newscleanarchkotlin.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.ionnt.newscleanarchkotlin.commons.exception.Failure
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.ui.news.usecases.GetNewsByCategory

/**
 * Created by Martin De Girolamo on 31/01/2019.
 */

class NewsDataSource(val getNewsByCategory: GetNewsByCategory, val category: String): PageKeyedDataSource<Int, Articles>() {
    var failure: MutableLiveData<Failure> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Articles>) {
        getNewsByCategory(GetNewsByCategory.Params(1, params.requestedLoadSize, category)) {
            it.either(::handleFailure) { list -> callback.onResult(list, null, 2) }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        getNewsByCategory(GetNewsByCategory.Params(params.key, params.requestedLoadSize, category)) {
            it.either(::handleFailure) { list -> callback.onResult(list, params.key + 1) }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
    }

    private fun handleFailure(failure: Failure) {
        this.failure.postValue(failure)
    }
}