package com.kin.carta.android.data

import com.google.gson.annotations.SerializedName

data class CaseStudiesResponseModel(
    @SerializedName("case_studies") val caseStudies: List<CaseStudy>
)