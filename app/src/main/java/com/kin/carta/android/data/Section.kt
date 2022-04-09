package com.kin.carta.android.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Section(
    @SerializedName("title") val title: String,
    @SerializedName("body_elements") val bodyElements: @RawValue List<Any>
): Parcelable