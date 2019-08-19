package com.kacper.launchme.repository

import com.kacper.launchme.api.SpaceXService
import com.kacper.launchme.data.list.BaseListRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val spaceXService: SpaceXService) :
    AppRepository {

    override fun getFlowLaunchesList(request: BaseListRequest) =
        flow {
            emit(spaceXService.getFlowLaunches(request.toMap()))
        }

    override fun getFlowLaunchDetails(flightNumber: Int) =
        flow {
            emit(spaceXService.getFlowLaunchDetails(flightNumber))
        }


    override fun getRxJavaLaunchesList(request: BaseListRequest) =
        spaceXService.getLaunches(request.toMap())

    override fun getRxJavaLaunchDetails(flightNumber: Int) =
        spaceXService.getLaunchDetails(flightNumber)

}


