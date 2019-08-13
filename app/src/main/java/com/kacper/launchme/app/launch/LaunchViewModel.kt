package com.kacper.launchme.app.launch

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kacper.launchme.app.launch.list.LaunchState
import com.kacper.launchme.app.launch.list.LaunchesListSourceFactory
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.repository.AppRepository
import com.kacper.launchme.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    var launchesList: LiveData<PagedList<Launch>>? = null
    private var launchesListsFactoryRxJava: LaunchesListSourceFactory? = null

    private val disposable = CompositeDisposable()

    var currentLaunch: Launch? = null

    var state = ObservableField<BaseState>(BaseState.Loading)

    var isFlowEnabled = ObservableBoolean(false)

    fun initLaunchesList() {
        launchesListsFactoryRxJava =
            LaunchesListSourceFactory(disposable, appRepository, state, isFlowEnabled.get())

        launchesList = LivePagedListBuilder<Int, Launch>(
            launchesListsFactoryRxJava!!,
            Utils.getDefaultPagedListConfig()
        ).build()
    }

    fun getLaunchDetails(flightNumber : Int){
        disposable.add(
                appRepository.getRxJavaLaunchDetails(flightNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        currentLaunch = it
                        state.set(LaunchState.OnLaunchDetailsFetched)
                    },{
                        state.set(BaseState.OnError)
                    })
        )
    }

    fun onErrorLaunchListCallback() {
        state.set(BaseState.Loading)
        launchesListsFactoryRxJava?.invalidate()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}