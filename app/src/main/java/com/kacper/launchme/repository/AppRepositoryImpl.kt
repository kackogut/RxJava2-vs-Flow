package com.kacper.launchme.repository

import com.kacper.launchme.api.SpaceXService
import com.kacper.launchme.data.list.BaseListRequest
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val spaceXService: SpaceXService) :
    AppRepository {

    override fun getLaunchesList(request: BaseListRequest) =
        spaceXService.getLaunches(request.toMap())

}