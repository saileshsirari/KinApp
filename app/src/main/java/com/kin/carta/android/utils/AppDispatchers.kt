package com.kin.carta.android.utils

object AppDispatchers {
    var dispatcherProvider: DispatcherProvider = AppDispatcherProvider()
    val IO = dispatcherProvider.io
    val Main = dispatcherProvider.main
}