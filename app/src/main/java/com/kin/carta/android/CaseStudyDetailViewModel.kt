package com.kin.carta.android

import com.google.gson.internal.LinkedTreeMap
import com.kin.carta.android.data.BodyElement
import com.kin.carta.android.data.CaseStudy
import com.kin.carta.android.data.ImageUrl
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
                        if(it.keys.contains(ImageUrl.KEY_IMAGE_URL)) {
                            list.add(
                                CaseStudySectionItemViewModel(
                                    null,
                                    BodyElement(ImageUrl(it[ImageUrl.KEY_IMAGE_URL] as String), null)
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