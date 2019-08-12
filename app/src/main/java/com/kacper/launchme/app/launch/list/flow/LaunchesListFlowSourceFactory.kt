package com.kacper.launchme.app.launch.list.flow

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable

class LaunchesListFlowSourceFactory(
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>
) : DataSource.Factory<Int, Launch>() {

    private val launchListLiveData = MutableLiveData<LaunchesListFlowSource>()
    private var launchListFlowSource: LaunchesListFlowSource? = null

    override fun create(): DataSource<Int, Launch> {
        launchListFlowSource =
            LaunchesListFlowSource( appRepository, state)
        launchListLiveData.postValue(launchListFlowSource)
        return launchListFlowSource!!
    }

    fun invalidate() {
        launchListFlowSource?.invalidate()
    }
}