package com.orost.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orost.covid19.api.ApiService
import com.orost.covid19.model.GlobalByDate
import com.orost.covid19.utils.CoroutineContextProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class ChartViewModel(
    private val apiService: ApiService,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {
    val statisticByDate by lazy {
        val liveData = MutableLiveData<GlobalByDate>()
        fetchStatisticsByDate()
        return@lazy liveData
    }

    private fun fetchStatisticsByDate() {
        viewModelScope.launch(coroutineContextProvider.io) {
            try {
                statisticByDate.postValue(apiService.getGlobalByDate())
            } catch (e: Exception) {
                Timber.e(e, "Error while fetching statistics by date")
            }
        }
    }

}
