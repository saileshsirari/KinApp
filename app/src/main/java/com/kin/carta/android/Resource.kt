package com.kin.carta.android

data class Resource<out T>(val status: Status, val data: T?, val error: NetworkError? = null) {
    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data)
        fun <T> error(data: T?, error: NetworkError?) = Resource(Status.ERROR, data, error)
        fun <T> loading(data: T?) = Resource(Status.LOADING, data)
    }
}

