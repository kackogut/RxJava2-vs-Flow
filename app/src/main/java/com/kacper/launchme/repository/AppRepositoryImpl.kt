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

    private val workingStateSubject = BehaviorSubject.create<Boolean>()
    private val dataChangeSubject = PublishSubject.create<Boolean>()
    private val workingLockCount = AtomicInteger(0)

    override fun getWorkingStateSubject(): BehaviorSubject<Boolean> {
        return workingStateSubject
    }


    override fun getDataChangeSubject(): PublishSubject<Boolean> {
        return dataChangeSubject
    }

    override fun getLaunchesList(request: BaseListRequest) =
        spaceXService.getLaunches(request.toMap())
            .doOnSubscribe { incrementWorkingLock() }
            .doFinally(this::decrementWorkingLock)

    @Synchronized
    private fun incrementWorkingLock() {
        val previousCount = workingLockCount.getAndIncrement()
        if (previousCount <= 0) {
            workingStateSubject.onNext(true)
        }
    }

    @Synchronized
    private fun decrementWorkingLock() {
        val count = workingLockCount.decrementAndGet()
        if (count <= 0) {
            workingStateSubject.onNext(false)
        }
    }
}