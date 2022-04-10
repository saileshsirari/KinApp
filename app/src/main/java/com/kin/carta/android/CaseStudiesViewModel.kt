package com.kin.carta.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kin.carta.android.data.CaseStudiesUseCase
import com.kin.carta.android.data.CaseStudy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class CaseStudiesViewModel @Inject constructor(
    private val useCase: CaseStudiesUseCase
) :
    BaseViewModel() {
    val getCaseStudiesRequest = SingleLiveEvent<Boolean>()
    private val needRefreshCache = true
    val getCaseStudies: LiveData<PagingData<CaseStudy>> =
        getCaseStudiesRequest.switchMap {
            if (needRefreshCache) {
                liveData(viewModelScope.coroutineContext + AppDispatchers.IO) {
                    useCase.getCaseStudies().cachedIn(viewModelScope).collect { res ->
                        emit(res)
                    }
                }

            } else {
                AbsentLiveData.create()
            }
        }
}