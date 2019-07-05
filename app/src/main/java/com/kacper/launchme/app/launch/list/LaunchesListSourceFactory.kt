package com.kacper.launchme.app.launch.list

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable

class LaunchesListSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>
) : DataSource.Factory<Int, Launch>() {

    private val launchListLiveData = MutableLiveData<LaunchesListSource>()
    private var launchListSource: LaunchesListSource? = null

    override fun create(): DataSource<Int, Launch> {
        launchListSource =
            LaunchesListSource(compositeDisposable, appRepository, state)
        launchListLiveData.postValue(launchListSource)
        return launchListSource!!
    }

    fun invalidate() {
        launchListSource?.invalidate()
    }
}