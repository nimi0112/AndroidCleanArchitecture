package com.example.plumercv.presentation.main.fragment

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */


@Subcomponent/*(modules = ...)*/
interface RepoFragmentSubcomponent : AndroidInjector<RepoFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoFragment>()
}

@Module(subcomponents = arrayOf(RepoFragmentSubcomponent::class))
abstract class RepoFragmentModule {
    @ClassKey(RepoFragment::class)
    @Binds
    @IntoMap
    abstract fun bindCryptoListFragmentInjectorFactory(builder: RepoFragmentSubcomponent.Builder): AndroidInjector
    .Factory<*>
}