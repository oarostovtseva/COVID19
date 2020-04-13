package com.orost.covid19.model

data class GlobalStatistic(
    val count: Int,
    val date: String,
    val result: Statistics
)

data class GlobalByCountry(
    val count: Int,
    val date: String,
    val result: List<Map<String, Statistics>>
)

data class GlobalByDate(
    val count: Int,
    val date: String,
    val result: Map<String, Statistics>
)

data class Statistics(
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int
)
