package com.kacper.launchme.di.module

import com.kacper.launchme.repository.AppRepository
import com.kacper.launchme.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    internal abstract fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

}