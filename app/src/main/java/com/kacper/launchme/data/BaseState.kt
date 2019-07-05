package com.kacper.launchme.data

sealed class BaseState {
    object OnError : BaseState()
    object Loading : BaseState()
    object Success : BaseState()
}