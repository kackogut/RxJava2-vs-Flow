package com.kacper.launchme.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kacper.launchme.app.launch.LaunchViewModel
import com.kacper.launchme.di.ViewModelKey
import com.kacper.launchme.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    abstract fun bindRepoViewModel(launchViewModel: LaunchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}