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

package com.kin.carta.android.casestudies

import androidx.lifecycle.*
import com.kin.carta.android.utils.AppDispatchers
import com.kin.carta.android.BaseViewModel
import com.kin.carta.android.data.CaseStudiesResponseModel
import com.kin.carta.android.domain.CaseStudiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class CaseStudiesViewModel @Inject constructor(
    private val useCase: CaseStudiesUseCase
) :
    BaseViewModel() {
    private val needRefreshCache = false
    private val cachedData = MutableLiveData<CaseStudiesResponseModel>()
    val getCaseStudiesRequest = MutableLiveData<Boolean>()
    val getCaseStudies: LiveData<CaseStudiesResponseModel?> =
        getCaseStudiesRequest.switchMap {
            if (needRefreshCache || cachedData.value == null) {
                liveData(viewModelScope.coroutineContext + AppDispatchers.IO) {
                    useCase.getCaseStudies().collect { response ->
                        cachedData.postValue(response)
                        emit(response)
                    }
                }
            } else {
                cachedData
            }
        }
}