package com.kacper.launchme.api

import com.kacper.launchme.data.launch.Launch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SpaceXService {

    //RxJava methods

    @GET("launches")
    fun getLaunches(@QueryMap request: Map<String, Int>): Single<ArrayList<Launch>>

    @GET("launches/{FLIGHT_NUMBER}")
    fun getLaunchDetails(@Path("FLIGHT_NUMBER") flightNumber: Int): Single<Launch>

    //Coroutines methods

    @GET("launches")
    suspend fun getFlowLaunches(@QueryMap request: Map<String, Int>): ArrayList<Launch>

    @GET("launches/{FLIGHT_NUMBER}")
    suspend fun getFlowLaunchDetails(@Path("FLIGHT_NUMBER") flightNumber: Int) : Launch

}