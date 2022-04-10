/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kin.carta.android.di

import com.kin.carta.android.api.ICaseStudiesApi
import com.kin.carta.android.data.CaseStudiesRepositoryImpl
import com.kin.carta.android.data.ICaseStudiesRepository
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

