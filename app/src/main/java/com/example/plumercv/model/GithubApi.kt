package com.example.plumercv.model

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

interface GithubApi {
    @GET("{username}/repos")
    fun getRepoList(@Path("username") username: String): Single<List<RepoModel>>
}

data class RepoModel(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String
)