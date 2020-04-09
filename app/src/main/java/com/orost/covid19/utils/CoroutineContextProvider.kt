package com.orost.covid19.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutineContextProvider {
    open val main: CoroutineDispatcher = Dispatchers.Main
    open val io: CoroutineDispatcher = Dispatchers.IO
}

@Suppress("EXPERIMENTAL_API_USAGE")
class TestContextProvider : CoroutineContextProvider() {
    override val main = Dispatchers.Unconfined
    override val io = Dispatchers.Unconfined
}