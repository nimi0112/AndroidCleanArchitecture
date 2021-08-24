package com.example.plumercv.di

import com.example.plumercv.domain.RepoListInteractor
import com.example.plumercv.domain.RepoListUseCases
import com.example.plumercv.model.GithubRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

@Module
class UseCasesModule {
    @Provides
    fun providesRepoListUseCases(githubRepository: GithubRepository): RepoListUseCases =
        RepoListInteractor(githubRepository)
}