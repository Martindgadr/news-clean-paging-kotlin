package com.ionnt.newscleanarchkotlin.commons.di.modules

import com.ionnt.newscleanarchkotlin.commons.di.annotations.FragmentScope
import com.ionnt.newscleanarchkotlin.ui.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeMainFragment(): NewsFragment
}