package com.kin.carta.android.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kin.carta.android.api.CaseStudiesService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CaseStudiesRepository @Inject constructor(private val service: CaseStudiesService) {

    fun getCaseStudies(): Flow<PagingData<CaseStudy>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { CaseStudiesPagingSource(service) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}
