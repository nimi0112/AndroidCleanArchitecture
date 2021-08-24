package com.example.plumercv.di

import com.example.plumercv.GitHubRepoApplication
import dagger.Component
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        NetModule::class,
        RepositoryModule::class,
        UseCasesModule::class,
        ViewModelModule::class,
        MainActivityModule::class
    )
)
interface ApplicationComponent {
    fun inject(app: GitHubRepoApplication)
}