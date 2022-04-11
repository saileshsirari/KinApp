
package com.kin.carta.android.di

import com.kin.carta.android.api.ICaseStudiesApi
import com.kin.carta.android.data.CaseStudiesRepositoryImpl
import com.kin.carta.android.api.ICaseStudiesRepository
import com.kin.carta.android.domain.CaseStudiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    fun provideCaseStudiesRepository(api: ICaseStudiesApi): ICaseStudiesRepository =
        CaseStudiesRepositoryImpl(api)

    @Provides
    fun provideCaseStudiesUseCase(repository: ICaseStudiesRepository) =
        CaseStudiesUseCase(repository)
}

