package com.kacper.launchme.app.launch.list

import com.kacper.launchme.data.BaseState

sealed class LaunchState : BaseState(){
    object OnLaunchDetailsFetched : LaunchState()
}