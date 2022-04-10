package com.kin.carta.android.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ICaseStudiesRepository {
     fun getCaseStudies(): Flow<PagingData<CaseStudy>>
}