package com.kin.carta.android

import com.kin.carta.android.data.CaseStudy

class CaseStudiesItemViewModel(val caseStudy: CaseStudy) {
    val imageUrl
        get() = caseStudy.heroImage
    val teaser
        get() = caseStudy.teaser

}
