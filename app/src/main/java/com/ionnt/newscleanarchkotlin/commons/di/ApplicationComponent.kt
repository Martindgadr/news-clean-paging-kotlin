package com.ionnt.newscleanarchkotlin.commons.di

import com.ionnt.newscleanarchkotlin.TMDBApplication
import com.ionnt.newscleanarchkotlin.commons.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 23/01/2019.
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (AndroidInjectionModule::class), (ActivityModule::class),
    (FragmentModule::class), (ViewModelModule::class), (RepositoryModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TMDBApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: TMDBApplication)
}