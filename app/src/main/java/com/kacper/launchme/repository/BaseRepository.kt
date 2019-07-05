package com.kacper.launchme.repository

import androidx.annotation.NonNull
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

interface BaseRepository {
    @NonNull
    fun getWorkingStateSubject(): BehaviorSubject<Boolean>

    fun getDataChangeSubject(): PublishSubject<Boolean>
}