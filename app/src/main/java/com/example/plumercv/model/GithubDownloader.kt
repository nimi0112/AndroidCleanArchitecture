package com.example.plumercv.model

import io.reactivex.Single

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

class GithubDownloader(private val githubApi: GithubApi) : GithubRepository {

    override fun getRepoList(username: String): Single<List<RepoModel>> = githubApi.getRepoList(username)

}