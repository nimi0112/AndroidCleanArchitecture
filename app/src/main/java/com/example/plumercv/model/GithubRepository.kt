package com.example.plumercv.model

import io.reactivex.Single

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

interface GithubRepository {

    fun getRepoList(username: String): Single<List<RepoModel>>
}