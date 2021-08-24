package com.example.plumercv.presentation.main

import com.example.plumercv.model.RepoModel

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

sealed class RepoFragmentState {
    abstract val data: List<RepoModel>
}

data class InitialState(override val data: List<RepoModel> = emptyList()) : RepoFragmentState()

data class RepoLoadedState(override val data: List<RepoModel>) : RepoFragmentState()

data class LoadingState(val isLoading: Boolean, override val data: List<RepoModel>) : RepoFragmentState()

data class ErrorState(val errorMessage: String, override val data: List<RepoModel>) : RepoFragmentState()
