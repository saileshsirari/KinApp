package com.kin.carta.android.casestudies

import androidx.lifecycle.MutableLiveData
import com.kin.carta.android.adapters.ImageLoadListener
import com.kin.carta.android.data.BodyElement

class CaseStudySectionItemViewModel(
    val sectionTitle: String?,
    val bodyElement: BodyElement
) {
    val imageLoaded = MutableLiveData<Boolean>(false)

    val listener = object : ImageLoadListener {
        override fun onImageLoaded() {
            imageLoaded.value = true
        }

        override fun onImageFailedToLoad() {
            imageLoaded.value = false
        }

    }
}
