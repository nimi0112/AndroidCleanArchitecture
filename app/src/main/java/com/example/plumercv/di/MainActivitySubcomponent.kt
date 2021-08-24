package com.example.plumercv.di

import com.example.plumercv.MainActivity
import com.example.plumercv.presentation.main.fragment.RepoFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

@Subcomponent(modules = arrayOf(RepoFragmentModule::class))
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}