package com.ionnt.newscleanarchkotlin.navigations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Martin De Girolamo on 30/01/2019.
 */
class InitialActivity: AppCompatActivity() {
    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        navigator.showMainNews(this)
    }
}