package com.kin.carta.android.casestudies

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kin.carta.android.adapters.ImageLoadListener
import com.kin.carta.android.data.CaseStudy
import com.kin.carta.android.databinding.ListItemCaseStudyBinding

class CaseStudiesItemViewModel(
    val caseStudy: CaseStudy,
    private val binding: ListItemCaseStudyBinding?
) {
    val imageUrl
        get() = caseStudy.heroImage
    val teaser
        get() = caseStudy.teaser
    val imageLoaded = MutableLiveData<Boolean>()
    val hideProgress = MutableLiveData<Boolean>(true)
    val listener = object : ImageLoadListener {
        override fun onImageLoaded() {
            imageLoaded.value = false
            hideProgress.value = true
            binding?.executePendingBindings()
        }

        override fun onImageFailedToLoad() {
            imageLoaded.postValue(false)
        }

    }
}
