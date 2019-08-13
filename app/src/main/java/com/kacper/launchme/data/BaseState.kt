package com.kacper.launchme.data

abstract class BaseState {
    object OnError : BaseState()
    object Loading : BaseState()
    object Success : BaseState()
}