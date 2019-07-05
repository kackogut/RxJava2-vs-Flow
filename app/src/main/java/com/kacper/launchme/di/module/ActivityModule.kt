package com.kacper.launchme.di.module

import com.kacper.launchme.app.launch.LaunchActivity
import com.kacper.launchme.di.fragments.LaunchFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [LaunchFragmentModule::class])
    abstract fun contributeLaunchActivity(): LaunchActivity

}