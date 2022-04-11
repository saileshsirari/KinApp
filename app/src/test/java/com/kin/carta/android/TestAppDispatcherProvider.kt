package com.kin.carta.android

import com.kin.carta.android.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class TestAppDispatcherProvider : DispatcherProvider {
    override val io: CoroutineDispatcher
    override val main: MainCoroutineDispatcher

    init {
        Dispatchers.setMain(TestCoroutineDispatcher())
        io = Dispatchers.Main
        main = Dispatchers.Main
    }
}

