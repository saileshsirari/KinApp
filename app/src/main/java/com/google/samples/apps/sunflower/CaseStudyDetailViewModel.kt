package com.google.samples.apps.sunflower

import com.google.gson.internal.LinkedTreeMap
import com.google.samples.apps.sunflower.data.BodyElement
import com.google.samples.apps.sunflower.data.CaseStudy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CaseStudyDetailViewModel @Inject constructor(

) :
    BaseViewModel() {
    val caseStudy = SingleLiveEvent<CaseStudy>()
    val items = SingleLiveEvent<List<CaseStudySectionItemViewModel>>()
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
                        list.add(
                            CaseStudySectionItemViewModel(
                                null,
                                BodyElement(null, null)
                            )
                        )
                    }
                }

            }
            items.postValue(list)
        }
    }
}