package com.kin.carta.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kin.carta.android.utils.AppDispatchers
import com.kin.carta.android.utils.SingleLiveEvent
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    val progressDialog = SingleLiveEvent(false)

    fun makeScopedCall(
        networkDispatcher: CoroutineDispatcher = AppDispatchers.IO,
        function: suspend CoroutineScope.() -> Unit
    ): Job =
        viewModelScope.launch(networkDispatcher, CoroutineStart.DEFAULT, function)

}

