package com.orost.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orost.covid19.api.ApiService
import com.orost.covid19.model.GlobalByCountry
import com.orost.covid19.utils.CoroutineContextProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class CountriesViewModel(
    private val apiService: ApiService,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {
    val worldStatistic by lazy {
        val liveData = MutableLiveData<GlobalByCountry>()
        fetchCountryStatistics()
        return@lazy liveData
    }

    private fun fetchCountryStatistics() {
        viewModelScope.launch(coroutineContextProvider.io) {
            try {
                worldStatistic.postValue(apiService.getGlobalByCountry())
            } catch (e: Exception) {
                Timber.e(e, "Error while fetching statistics by country")
            }
        }
    }

}
