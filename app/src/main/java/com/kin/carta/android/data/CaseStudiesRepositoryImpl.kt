package com.kin.carta.android.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kin.carta.android.api.ICaseStudiesApi
import kotlinx.coroutines.flow.Flow

class CaseStudiesRepositoryImpl(private val apiI: ICaseStudiesApi) : ICaseStudiesRepository {
    override  fun getCaseStudies(): Flow<PagingData<CaseStudy>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { CaseStudiesPagingSource(apiI) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}