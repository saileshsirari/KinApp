package com.google.samples.apps.sunflower.data

import com.google.gson.annotations.SerializedName

data class CaseStudiesResponseModel(
    @SerializedName("case_studies") val caseStudies: List<CaseStudy>
)