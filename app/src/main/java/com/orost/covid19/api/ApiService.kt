package com.orost.covid19.api

import com.orost.covid19.model.GlobalByCountry
import com.orost.covid19.model.GlobalByDate
import com.orost.covid19.model.GlobalStatistic
import retrofit2.http.GET

interface ApiService {

    @GET("global")
    suspend fun getGlobal(): GlobalStatistic

    @GET("global/latest")
    suspend fun getGlobalByCountry(): GlobalByCountry

    @GET("global/count")
    suspend fun getGlobalByDate(): GlobalByDate
}