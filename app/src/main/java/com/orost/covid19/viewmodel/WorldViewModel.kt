package com.orost.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orost.covid19.api.ApiService
import com.orost.covid19.model.GlobalLatestByCountry
import com.orost.covid19.utils.CoroutineContextProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class WorldViewModel(
    private val apiService: ApiService,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {
    val worldStatistic by lazy {
        val liveData = MutableLiveData<GlobalLatestByCountry>()
        fetchWorldStatistics()
        return@lazy liveData
    }

    private fun fetchWorldStatistics() {
        viewModelScope.launch(coroutineContextProvider.io) {
            try {
                worldStatistic.postValue(apiService.getGlobalLatest())
            } catch (e: Exception) {
                Timber.e(e, "Error while fetching statistics by country")
            }
        }
    }

}
