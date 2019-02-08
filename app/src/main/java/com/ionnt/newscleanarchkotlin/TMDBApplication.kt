package com.ionnt.newscleanarchkotlin

import android.app.Activity
import android.app.Application
import com.ionnt.newscleanarchkotlin.commons.di.ApplicationComponent
import com.ionnt.newscleanarchkotlin.commons.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

class TMDBApplication: Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
        applicationComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}