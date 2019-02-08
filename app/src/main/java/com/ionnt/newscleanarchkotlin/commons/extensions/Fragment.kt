package com.ionnt.newscleanarchkotlin.commons.extensions

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ionnt.newscleanarchkotlin.commons.base.BaseActivity
import com.ionnt.newscleanarchkotlin.commons.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commitAllowingStateLoss()
}

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer
val BaseFragment.appContext: Context get() = activity?.applicationContext!!

