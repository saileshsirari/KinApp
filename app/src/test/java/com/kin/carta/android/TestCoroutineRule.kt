package com.kin.carta.android

import com.kin.carta.android.utils.AppDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class TestCoroutineRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {
    override fun starting(description: Description?) {
        super.starting(description)
        AppDispatchers.dispatcherProvider = TestAppDispatcherProvider()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
    }
}