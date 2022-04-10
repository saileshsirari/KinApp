
package com.kin.carta.android

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: MainCoroutineDispatcher
}