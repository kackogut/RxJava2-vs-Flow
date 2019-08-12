package com.kacper.launchme.app.launch.list.rx

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable

class LaunchesListRxJavaSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>
) : DataSource.Factory<Int, Launch>() {

    private val launchListLiveData = MutableLiveData<LaunchesListRxJavaSource>()
    private var launchListRxJavaSource: LaunchesListRxJavaSource? = null

    override fun create(): DataSource<Int, Launch> {
        launchListRxJavaSource =
            LaunchesListRxJavaSource(compositeDisposable, appRepository, state)
        launchListLiveData.postValue(launchListRxJavaSource)
        return launchListRxJavaSource!!
    }

    fun invalidate() {
        launchListRxJavaSource?.invalidate()
    }
}