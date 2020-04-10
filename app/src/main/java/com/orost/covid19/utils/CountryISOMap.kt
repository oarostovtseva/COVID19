package com.orost.covid19.utils

import java.util.*

val localeCountryISOMap: MutableMap<String, Locale> by lazy {
    val countries: Array<String> = Locale.getISOCountries()
    val map : MutableMap<String, Locale> = mutableMapOf()
    for (country in countries) {
        val locale = Locale("", country)
        map[locale.isO3Country.toUpperCase()] = locale
    }
    map
}
