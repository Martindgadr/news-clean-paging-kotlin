package com.ionnt.newscleanarchkotlin.commons.di.modules

import com.ionnt.newscleanarchkotlin.commons.di.annotations.ActivityScope
import com.ionnt.newscleanarchkotlin.navigations.InitialActivity
import com.ionnt.newscleanarchkotlin.ui.news.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    @ActivityScope
    abstract fun contributeMainActivity(): NewsActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeInitialActivity(): InitialActivity
}