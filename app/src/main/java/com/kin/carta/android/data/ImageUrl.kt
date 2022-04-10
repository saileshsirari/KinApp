package com.kin.carta.android.data

import com.google.gson.annotations.SerializedName

data class ImageUrl(
    @SerializedName("image_url") val imageUrl: String
){
    companion object{
        const val KEY_IMAGE_URL="image_url"
    }
}