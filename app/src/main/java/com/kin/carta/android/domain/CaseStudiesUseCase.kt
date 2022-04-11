package com.kin.carta.android.domain

import com.kin.carta.android.api.ICaseStudiesRepository
import com.kin.carta.android.data.CaseStudiesResponseModel
import kotlinx.coroutines.flow.Flow

class CaseStudiesUseCase(private val repository: ICaseStudiesRepository) {

    fun getCaseStudies(): Flow<CaseStudiesResponseModel> {
        return repository.getCaseStudies()
    }
}