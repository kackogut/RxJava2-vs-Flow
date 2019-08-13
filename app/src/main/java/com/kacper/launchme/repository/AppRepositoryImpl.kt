package com.kacper.launchme.repository

import com.kacper.launchme.api.SpaceXService
import com.kacper.launchme.data.launch.Launch
import com.kacper.launchme.data.list.BaseListRequest
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val spaceXService: SpaceXService) :
    AppRepository {

    override fun getFlowLaunchesList(request: BaseListRequest) =

        flow {
            emit(withContext(Dispatchers.IO) {
                spaceXService.getFlowLaunches(request.toMap())
            })
        }


    override fun getRxJavaLaunchesList(request: BaseListRequest) =
        spaceXService.getLaunches(request.toMap())

    override fun getRxJavaLaunchDetails(flightNumber: Int) =
            spaceXService.getLaunchDetails(flightNumber)

}