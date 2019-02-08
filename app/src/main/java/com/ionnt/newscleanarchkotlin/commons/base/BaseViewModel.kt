package com.ionnt.newscleanarchkotlin.commons.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ionnt.newscleanarchkotlin.commons.exception.Failure

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

abstract class BaseViewModel: ViewModel() {
    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }

    abstract fun cancelRequest()
}