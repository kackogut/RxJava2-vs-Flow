package com.kacper.launchme.di.fragments

import com.kacper.launchme.app.launch.details.LaunchDetailsFragment
import com.kacper.launchme.app.launch.list.LaunchesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class LaunchFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLaunchListFragment(): LaunchesListFragment

    @ContributesAndroidInjector
    abstract fun cotributeLaunchDetailsFragmen(): LaunchDetailsFragment

}