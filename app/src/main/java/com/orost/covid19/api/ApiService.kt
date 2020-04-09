package com.orost.covid19.api

import com.orost.covid19.model.GlobalLatest
import retrofit2.http.GET

interface ApiService {

    @GET("global")
    suspend fun getGlobalLatest(): GlobalLatest
}