package com.orost.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orost.covid19.api.ApiService
import com.orost.covid19.model.GlobalStatistic
import com.orost.covid19.utils.CoroutineContextProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val apiService: ApiService,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    val globalLatestLiveData by lazy {
        val liveData = MutableLiveData<GlobalStatistic>()
        fetchGlobalLatestStatistics()
        return@lazy liveData
    }

    fun forceFetchLatestGlobalStatistics() {
        fetchGlobalLatestStatistics()
    }

    private fun fetchGlobalLatestStatistics() {
        viewModelScope.launch(coroutineContextProvider.io) {
            try {
                globalLatestLiveData.postValue(apiService.getGlobal())
            } catch (e: Exception) {
                Timber.e(e, "Error while fetching statistics")
            }
        }
    }

}
