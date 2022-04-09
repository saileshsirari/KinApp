package com.google.samples.apps.sunflower.data

import com.google.gson.annotations.SerializedName

data class ImageUrl(
    @SerializedName("image_url") val imageUrl: String
)