package com.orost.covid19.di

import com.orost.covid19.api.ApiService
import com.orost.covid19.ui.ChartFragment
import com.orost.covid19.ui.HomeFragment
import com.orost.covid19.ui.CountryFragment
import com.orost.covid19.utils.CoroutineContextProvider
import com.orost.covid19.viewmodel.ChartViewModel
import com.orost.covid19.viewmodel.CountriesViewModel
import com.orost.covid19.viewmodel.MainViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_ADDRESS = "https://covidapi.info/api/v1/"

val appModule = module {

    single { createOkHttpClient() }
    single { buildApiService(createOkHttpClient()) }
    single { CoroutineContextProvider() }

    factory { HomeFragment() }
    factory { CountryFragment() }
    factory { ChartFragment() }

    viewModel { MainViewModel(get(), get()) }
    viewModel { CountriesViewModel(get(), get()) }
    viewModel { ChartViewModel(get(), get()) }

}

private fun createOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun buildApiService(okHttpClient: OkHttpClient): ApiService {
    val moshi = Moshi.Builder()
            .build()
    return Retrofit.Builder()
            .baseUrl(BASE_ADDRESS)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
}