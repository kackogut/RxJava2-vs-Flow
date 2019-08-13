package com.kacper.launchme.api

import com.kacper.launchme.data.launch.Launch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SpaceXService {

    @GET("launches")
    fun getLaunches(@QueryMap request: Map<String, Int>): Single<ArrayList<Launch>>

    @GET("launches")
    suspend fun getFlowLaunches(@QueryMap request: Map<String, Int>): ArrayList<Launch>
}