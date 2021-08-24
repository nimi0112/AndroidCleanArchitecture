package com.example.plumercv.domain

import com.example.plumercv.model.RepoModel
import io.reactivex.Single

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

interface RepoListUseCases {

    fun getRepoListByUserName(userName: String): Single<List<RepoModel>>
}