package com.orost.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orost.covid19.api.ApiService
import com.orost.covid19.model.GlobalLatest
import com.orost.covid19.utils.CoroutineContextProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class WorldViewModel(
    private val apiService: ApiService,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

}
