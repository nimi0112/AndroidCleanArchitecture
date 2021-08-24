package com.example.plumercv.di

import com.example.plumercv.model.GithubApi
import com.example.plumercv.model.GithubDownloader
import com.example.plumercv.model.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesGithubRepository(githubApi: GithubApi): GithubRepository = GithubDownloader(githubApi)

}