package com.example.plumercv.domain

import com.example.plumercv.model.GithubRepository
import com.example.plumercv.model.RepoModel
import io.reactivex.Single

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

class RepoListInteractor(private val githubRepository: GithubRepository) : RepoListUseCases {

    override fun getRepoListByUserName(userName: String): Single<List<RepoModel>> {
        return githubRepository.getRepoList(userName)
    }

}