package com.google.samples.apps.sunflower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    val progressDialog = SingleLiveEvent(false)
    val error = SingleLiveEvent<String>()
    val networkError = SingleLiveEvent<NetworkError>()

    fun makeScopedCall(
        networkDispatcher: CoroutineDispatcher = AppDispatchers.IO,
        function: suspend CoroutineScope.() -> Unit
    ): Job =
        viewModelScope.launch(networkDispatcher, CoroutineStart.DEFAULT, function)


    suspend fun progressDialog(visible: Boolean) {
        withMainContext {
            progressDialog.value = visible
        }
    }

    suspend fun withMainContext(callback: suspend () -> Unit) {
        withContext(AppDispatchers.Main.immediate) {
            callback.invoke()
        }
    }

}

class PlaceHolderViewModel : BaseViewModel()