package com.kin.carta.android.data

import com.kin.carta.android.api.ICaseStudiesApi
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

