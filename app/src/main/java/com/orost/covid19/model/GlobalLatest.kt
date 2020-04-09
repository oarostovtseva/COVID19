package com.orost.covid19.model

data class GlobalLatest(
    val count: Int,
    val date: String,
    val result: TotalStatistics
)

data class TotalStatistics(
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int
)