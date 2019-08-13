package com.kacper.launchme.repository

import com.kacper.launchme.api.SpaceXService
import com.kacper.launchme.data.list.BaseListRequest
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


    override fun getLaunchesList(request: BaseListRequest) =
        spaceXService.getLaunches(request.toMap())

}