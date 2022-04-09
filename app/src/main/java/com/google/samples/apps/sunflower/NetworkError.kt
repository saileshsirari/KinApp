package com.google.samples.apps.sunflower

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