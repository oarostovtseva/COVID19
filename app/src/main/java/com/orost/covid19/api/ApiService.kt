package com.orost.covid19.api

import com.orost.covid19.model.GlobalStatistic
import com.orost.covid19.model.GlobalLatestByCountry
import retrofit2.http.GET

interface ApiService {

    @GET("global")
    suspend fun getGlobal(): GlobalStatistic

    @GET("global/latest")
    suspend fun getGlobalLatest(): GlobalLatestByCountry
}