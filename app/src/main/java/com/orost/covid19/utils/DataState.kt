package com.orost.covid19.utils

/**
 * Represents the state of data fetching process
 * @param T Type of data
 */
sealed class DataState<out T> {
    data class Error(val error: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
}
