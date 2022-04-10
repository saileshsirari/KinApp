package com.kin.carta.android.api

import com.kin.carta.android.data.CaseStudiesResponseModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Used to connect to the Unsplash API to fetch photos
 */
interface ICaseStudiesApi {

    @GET("caseStudies.json")
    suspend fun getCaseStudies(
    ): CaseStudiesResponseModel

    companion object {
        private const val BASE_URL =
            "https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/"

        fun create(): ICaseStudiesApi {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ICaseStudiesApi::class.java)
        }
    }
}
