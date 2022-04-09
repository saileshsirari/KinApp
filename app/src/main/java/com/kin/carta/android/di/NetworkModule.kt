package com.kin.carta.android.di

import com.kin.carta.android.api.CaseStudiesService
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
    fun provideCaseStudiesService(): CaseStudiesService {
        return CaseStudiesService.create()
    }
}
