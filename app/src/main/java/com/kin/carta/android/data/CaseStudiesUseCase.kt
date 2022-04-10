package com.kin.carta.android.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class CaseStudiesUseCase(private val repository: ICaseStudiesRepository) {
     fun getCaseStudies(): Flow<PagingData<CaseStudy>> {
        return repository.getCaseStudies()
    }
}