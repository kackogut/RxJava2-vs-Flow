package com.kacper.launchme.app.launch

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kacper.launchme.app.launch.list.LaunchesListSourceFactory
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.repository.AppRepository
import com.kacper.launchme.utils.Utils
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    var launchesList: LiveData<PagedList<Launch>>? = null
    private var launchesListsFactory: LaunchesListSourceFactory? = null

    private val disposable = CompositeDisposable()

    var currentLaunch: Launch? = null

    var state = ObservableField<BaseState>(BaseState.Loading)

    fun initLaunchesList() {
        if (launchesList != null && launchesListsFactory != null) return

        launchesListsFactory = LaunchesListSourceFactory(disposable, appRepository, state)

        launchesList = LivePagedListBuilder<Int, Launch>(
            launchesListsFactory!!,
            Utils.getDefaultPagedListConfig()
        ).build()

    }

    fun onErrorLaunchListCallback() {
        state.set(BaseState.Loading)
        launchesListsFactory?.invalidate()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}