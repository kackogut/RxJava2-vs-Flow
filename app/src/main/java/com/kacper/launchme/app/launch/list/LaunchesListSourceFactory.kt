package com.kacper.launchme.app.launch.list

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kacper.launchme.app.launch.list.flow.LaunchesListFlowSource
import com.kacper.launchme.app.launch.list.rx.LaunchesListRxJavaSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable

class LaunchesListSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>,
    var isFlowEnabled : Boolean
) : DataSource.Factory<Int, Launch>() {

    private val launchListLiveDataFlow = MutableLiveData<LaunchesListFlowSource>()
    private var launchListFlowSource : LaunchesListFlowSource?= null

    private val launchListLiveDataRxJava = MutableLiveData<LaunchesListRxJavaSource>()
    private var launchListRxJavaSource: LaunchesListRxJavaSource? = null

    override fun create(): DataSource<Int, Launch> {
        return if(isFlowEnabled) {

            launchListFlowSource =
                LaunchesListFlowSource(appRepository, state)
            launchListLiveDataFlow.postValue(launchListFlowSource)
            launchListFlowSource!!

        } else {

            launchListRxJavaSource =
                LaunchesListRxJavaSource(
                    compositeDisposable,
                    appRepository,
                    state
                )
            launchListLiveDataRxJava.postValue(launchListRxJavaSource)
            launchListRxJavaSource!!

        }
    }

    fun invalidate() {
        launchListRxJavaSource?.invalidate()
    }
}