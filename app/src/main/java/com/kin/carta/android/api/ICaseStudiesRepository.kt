package com.kin.carta.android.api

import com.kin.carta.android.data.CaseStudiesResponseModel
import kotlinx.coroutines.flow.Flow

interface ICaseStudiesRepository {
    fun getCaseStudies(): Flow<CaseStudiesResponseModel>
}