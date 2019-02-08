package com.ionnt.newscleanarchkotlin.commons.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ionnt.newscleanarchkotlin.commons.di.annotations.ViewModelKey
import com.ionnt.newscleanarchkotlin.commons.factory.ViewModelFactory
import com.ionnt.newscleanarchkotlin.ui.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindsNewsViewModel(newsViewModel: NewsViewModel): ViewModel
}