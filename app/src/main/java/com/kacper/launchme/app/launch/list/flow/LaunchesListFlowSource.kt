package com.kacper.launchme.app.launch.list.flow

import androidx.databinding.ObservableField
import androidx.paging.PositionalDataSource
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.data.list.BaseListRequest
import com.kacper.launchme.repository.AppRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class LaunchesListFlowSource(
    private val appRepository: AppRepository,
    private val state: ObservableField<BaseState>
) : PositionalDataSource<Launch>() {

    private val baseListRequest = BaseListRequest()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Launch>) {
        baseListRequest.offset += baseListRequest.limit

        runBlocking {
            appRepository.getFlowLaunchesList(baseListRequest).collect {
                callback.onResult(it)
                state.set(BaseState.Success)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Launch>) {
        baseListRequest.offset = 0

        runBlocking {
            appRepository.getFlowLaunchesList(baseListRequest).collect {
                callback.onResult(it, 0)
                state.set(BaseState.Success)
            }
        }
    }

}