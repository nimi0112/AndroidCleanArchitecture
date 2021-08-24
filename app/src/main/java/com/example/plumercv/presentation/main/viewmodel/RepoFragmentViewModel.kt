package com.example.plumercv.presentation.main.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plumercv.di.SCHEDULER_IO
import com.example.plumercv.di.SCHEDULER_MAIN_THREAD
import com.example.plumercv.domain.RepoListUseCases
import com.example.plumercv.model.RepoModel
import com.example.plumercv.presentation.main.ErrorState
import com.example.plumercv.presentation.main.InitialState
import com.example.plumercv.presentation.main.LoadingState
import com.example.plumercv.presentation.main.RepoFragmentState
import com.example.plumercv.presentation.main.RepoLoadedState
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

class RepoFragmentViewModel
@Inject constructor(
    private val repoListUseCases: RepoListUseCases,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : ViewModel() {

    val stateLiveData = MutableLiveData<RepoFragmentState>()

    init {
        stateLiveData.value = InitialState()
    }

    @SuppressLint("CheckResult")
    fun getRepoList(userName: String) {
        stateLiveData.value = LoadingState(true, emptyList())
        repoListUseCases.getRepoListByUserName(userName)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onRepoListReceived, this::onError)
    }

    private fun onRepoListReceived(repoList: List<RepoModel>) {
        stateLiveData.value = RepoLoadedState(data = repoList)
    }

    private fun onError(error: Throwable) {
        stateLiveData.value = ErrorState(error.message ?: "", stateLiveData.value?.data ?: emptyList())
    }


}