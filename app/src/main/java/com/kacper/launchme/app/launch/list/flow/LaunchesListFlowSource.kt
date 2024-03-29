package com.kacper.launchme.app.launch.list.flow

import androidx.databinding.ObservableField
import androidx.paging.PositionalDataSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.data.list.BaseListRequest
import com.kacper.launchme.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class LaunchesListFlowSource(
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>
) : PositionalDataSource<Launch>() {

    private val baseListRequest = BaseListRequest()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Launch>) {
        baseListRequest.offset += baseListRequest.limit

        CoroutineScope(Dispatchers.IO).launch {
            appRepository.getFlowLaunchesList(baseListRequest)
                .catch {
                    Timber.e(it)
                    state.set(BaseState.OnError)
                }
                .collect {
                    callback.onResult(it)
                    state.set(BaseState.Success)
                }
        }

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Launch>) {
        baseListRequest.offset = 0

        CoroutineScope(Dispatchers.IO).launch {
            appRepository.getFlowLaunchesList(baseListRequest)
                .catch {
                    Timber.e(it)
                    state.set(BaseState.OnError)
                }
                .collect {
                    callback.onResult(it, 0)
                    state.set(BaseState.Success)
                }
        }

    }

}