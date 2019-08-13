package com.kacper.launchme.app.launch.list.rx

import androidx.databinding.ObservableField
import androidx.paging.PositionalDataSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.data.list.BaseListRequest
import com.kacper.launchme.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class LaunchesListRxJavaSource(
    private val compositeDisposable: CompositeDisposable,
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>
) : PositionalDataSource<Launch>() {

    private val baseListRequest = BaseListRequest()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Launch>) {
        baseListRequest.offset += baseListRequest.limit

        compositeDisposable.add(
            appRepository.getRxJavaLaunchesList(baseListRequest)
                .subscribe({
                    callback.onResult(it)
                    state.set(BaseState.Success)
                }, {
                    Timber.e(it)
                    state.set(BaseState.OnError)
                })
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Launch>) {
        baseListRequest.offset = 0

        compositeDisposable.add(
            appRepository.getRxJavaLaunchesList(baseListRequest)
                .subscribe({
                    callback.onResult(it, 0)
                    state.set(BaseState.Success)
                }, {
                    Timber.e(it)
                    state.set(BaseState.OnError)
                })
        )
    }


}