package com.example.plumercv.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.plumercv.common.mock
import com.example.plumercv.common.whenever
import com.example.plumercv.domain.RepoListInteractor
import com.example.plumercv.model.GithubRepository
import com.example.plumercv.repoModel
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

class RepoListUseCasesUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val githubRepository = mock<GithubRepository>()

    private val repoListUseCases by lazy { RepoListInteractor(githubRepository) }

    @Test
    fun testRepoListUseCases_getReposList_Completed() {
        whenever(githubRepository.getRepoList(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(emptyList()))

        repoListUseCases.getRepoListByUserName("nimi0112")
            .test()
            .assertComplete()
    }

    @Test
    fun testRepoListUseCases_getRepoList_Error() {
        val response = Throwable("Error response")
        whenever(githubRepository.getRepoList(ArgumentMatchers.anyString()))
            .thenReturn(Single.error(response))

        repoListUseCases.getRepoListByUserName("nimi0112")
            .test()
            .assertError(response)

    }

    @Test
    fun testRepoListUseCases_getRepoList_response() {
        val response = arrayListOf(repoModel())
        whenever(githubRepository.getRepoList(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(response))

        val expectedList = arrayListOf(repoModel())

        repoListUseCases.getRepoListByUserName("0")
            .test()
            .assertValue(expectedList)
    }
}