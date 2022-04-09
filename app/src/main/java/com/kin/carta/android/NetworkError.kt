package com.kin.carta.android

data class NetworkError(
    val code: String,
    val message: String,
    val stackTrace: String? = null,
    val errors: Map<String, String>? = null,
    val status: String? = null,
    val timestamp: String? = null,
    val throwable: Throwable? = null,
    val statusCode: Int? = null
)