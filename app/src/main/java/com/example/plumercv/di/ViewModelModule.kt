package com.example.plumercv.di

import androidx.lifecycle.ViewModel
import com.example.plumercv.presentation.main.viewmodel.RepoFragmentViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepoFragmentViewModel::class)
    abstract fun bindRepoListViewModel(viewModel: RepoFragmentViewModel): ViewModel
}