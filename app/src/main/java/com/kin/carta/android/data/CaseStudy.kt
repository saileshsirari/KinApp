package com.kin.carta.android.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaseStudy(
    @SerializedName("id") val id: Int,
    @SerializedName("client") val client: String,
    @SerializedName("teaser") val teaser: String,
    @SerializedName("title") val title: String,
    @SerializedName("hero_image") val heroImage: String,
    @SerializedName("sections") val sections: List<Section>
): Parcelable