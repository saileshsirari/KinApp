package com.google.samples.apps.sunflower

object AppDispatchers {
    var dispatcherProvider: DispatcherProvider = AppDispatcherProvider()
    val IO = dispatcherProvider.io
    val Main = dispatcherProvider.main
}