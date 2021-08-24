package com.example.plumercv.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.plumercv.common.mock
import com.example.plumercv.common.whenever
import com.example.plumercv.domain.RepoListUseCases
import com.example.plumercv.model.RepoModel
import com.example.plumercv.presentation.main.RepoFragmentState
import com.example.plumercv.presentation.main.viewmodel.RepoFragmentViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

class RepoListVMUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val repoListUseCases = mock<RepoListUseCases>()
    val observerState = mock<Observer<RepoFragmentState>>()

    val viewmodel by lazy {
        RepoFragmentViewModel(
            repoListUseCases,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Before
    fun initTest() {
        Mockito.reset(repoListUseCases, observerState)
    }

    @Test
    fun testRepoList_updateRepoList_Load() {
        val response = arrayListOf(RepoModel("", ""))
        whenever(repoListUseCases.getRepoListByUserName(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(response))

        viewmodel.stateLiveData.observeForever(observerState)
        repoListUseCases.getRepoListByUserName("")
        Mockito.verify(repoListUseCases).getRepoListByUserName("")

        val argumentCaptor = ArgumentCaptor.forClass(RepoFragmentState::class.java)
        argumentCaptor.run {
            Mockito.verify(observerState, Mockito.times(1)).onChanged(capture())
        }
    }

    @Test
    fun testRepoList_updateRepoList_Error() {
        val errorMessage = "Error response"
        val response = Throwable(errorMessage)
        whenever(repoListUseCases.getRepoListByUserName(ArgumentMatchers.anyString()))
            .thenReturn(Single.error(response))

        viewmodel.stateLiveData.observeForever(observerState)

        repoListUseCases.getRepoListByUserName("")
        Mockito.verify(repoListUseCases).getRepoListByUserName("")
    }
}