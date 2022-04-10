package com.kin.carta.android.utilities

import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.kin.carta.android.data.CaseStudiesResponseModel
import com.kin.carta.android.data.CaseStudy
import java.io.InputStreamReader


object TestUtil {
    fun getCaseStudy(): CaseStudy {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("case_studies.json")
        val parser = JsonParser.parseReader(InputStreamReader(inputStream))
        val type = object : TypeToken<CaseStudiesResponseModel>() {}.type
        val gson = Gson().fromJson<CaseStudiesResponseModel>(parser, type)
        return gson.caseStudies[0]
    }

}