package com.google.samples.apps.sunflower

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.samples.apps.sunflower.data.CaseStudiesRepository
import com.google.samples.apps.sunflower.data.CaseStudy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CaseStudiesViewModel @Inject constructor(
    private val repository: CaseStudiesRepository
) :
    BaseViewModel() {
    val getCaseStudiesRequest = SingleLiveEvent<Boolean>()
    private var currentSearchResult: Flow<PagingData<CaseStudy>>? = null
    private val needRefreshCache =false

    fun getCaseStudies(): Flow<PagingData<CaseStudy>> {
        return if(needRefreshCache || currentSearchResult ==null) {
            val newResult: Flow<PagingData<CaseStudy>> =
                repository.getCaseStudies().cachedIn(viewModelScope)
            currentSearchResult = newResult
            newResult
        }else{
            currentSearchResult!!
        }
    }

}