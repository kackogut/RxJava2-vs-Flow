package com.kacper.launchme.repository

import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.data.list.BaseListRequest
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getRxJavaLaunchesList(request: BaseListRequest): Single<ArrayList<Launch>>

    fun getRxJavaLaunchDetails(flightNumber: Int):Single<Launch>

    fun getFlowLaunchesList(request: BaseListRequest): Flow<ArrayList<Launch>>
}