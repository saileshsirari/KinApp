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

import androidx.lifecycle.MutableLiveData
import com.google.gson.internal.LinkedTreeMap
import com.kin.carta.android.BaseViewModel
import com.kin.carta.android.data.BodyElement
import com.kin.carta.android.data.CaseStudy
import com.kin.carta.android.data.ImageUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CaseStudyDetailsViewModel @Inject constructor() : BaseViewModel() {
    val caseStudy = MutableLiveData<CaseStudy>()
    val items = MutableLiveData<List<CaseStudySectionItemViewModel>>()

    fun initItems(caseStudy: CaseStudy) {
        makeScopedCall {
            val list = mutableListOf<CaseStudySectionItemViewModel>()
            val sections = caseStudy.sections
            sections.forEach { section ->
                list.add(
                    CaseStudySectionItemViewModel(
                        section.title,
                        BodyElement(null, null)
                    )
                )
                section.bodyElements.forEach {
                    if (it is String) {
                        list.add(
                            CaseStudySectionItemViewModel(
                                null,
                                BodyElement(null, it)
                            )
                        )
                    } else if (it is LinkedTreeMap<*, *>) {
                        if (it.keys.contains(ImageUrl.KEY_IMAGE_URL)) {
                            list.add(
                                CaseStudySectionItemViewModel(
                                    null,
                                    BodyElement(
                                        ImageUrl(it[ImageUrl.KEY_IMAGE_URL] as String),
                                        null
                                    )
                                )
                            )
                        }
                    }
                }

            }
            items.postValue(list)
        }
    }
}