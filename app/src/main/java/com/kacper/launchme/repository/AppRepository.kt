package com.kacper.launchme.repository

import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.data.list.BaseListRequest
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getLaunchesList(request: BaseListRequest): Single<ArrayList<Launch>>

    fun getFlowLaunchesList(request: BaseListRequest): Flow<ArrayList<Launch>>
}