package com.kin.carta.android.data

import com.kin.carta.android.api.ICaseStudiesApi
import com.kin.carta.android.api.ICaseStudiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CaseStudiesRepositoryImpl(private val apiI: ICaseStudiesApi) : ICaseStudiesRepository {

    override fun getCaseStudies(): Flow<CaseStudiesResponseModel> {
        return flow { emit(apiI.getCaseStudies()) }
    }
}