package com.kin.carta.android.di

import com.kin.carta.android.api.ICaseStudiesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCaseStudiesService(): ICaseStudiesApi {
        return ICaseStudiesApi.create()
    }
}
