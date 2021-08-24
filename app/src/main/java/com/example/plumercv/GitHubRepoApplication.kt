package com.example.plumercv

import android.app.Application
import com.example.plumercv.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

class GitHubRepoApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.create()
            .inject(this)
    }

    /** Returns an [AndroidInjector].  */
    override fun androidInjector() = dispatchingActivityInjector


}