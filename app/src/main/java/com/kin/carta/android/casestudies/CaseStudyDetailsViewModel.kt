package com.kin.carta.android.casestudies

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.internal.LinkedTreeMap
import com.kin.carta.android.BaseViewModel
import com.kin.carta.android.adapters.ImageLoadListener
import com.kin.carta.android.data.BodyElement
import com.kin.carta.android.data.CaseStudy
import com.kin.carta.android.data.ImageUrl
import com.kin.carta.android.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CaseStudyDetailsViewModel @Inject constructor() : BaseViewModel() {
    private val imageLoaded = SingleLiveEvent<Boolean>()
    val caseStudy = MutableLiveData<CaseStudy>()
    val items = MutableLiveData<List<CaseStudySectionItemViewModel>>()

    fun initItems(caseStudy: CaseStudy) {
        makeScopedCall {
            val list = mutableListOf<CaseStudySectionItemViewModel>()
            val sections = caseStudy.sections
            sections.forEach { section ->
                list.add(
                    CaseStudySectionItemViewModel(
                        section.title,
                        BodyElement(null, null)
                    )
                )
                section.bodyElements.forEach {
                    if (it is String) {
                        list.add(
                            CaseStudySectionItemViewModel(
                                null,
                                BodyElement(null, it)
                            )
                        )
                    } else if (it is LinkedTreeMap<*, *>) {
                        if (it.keys.contains(ImageUrl.KEY_IMAGE_URL)) {
                            list.add(
                                CaseStudySectionItemViewModel(
                                    null,
                                    BodyElement(
                                        ImageUrl(it[ImageUrl.KEY_IMAGE_URL] as String),
                                        null
                                    )
                                )
                            )
                        }
                    }
                }

            }
            items.postValue(list)
        }
    }

    val listener = object : ImageLoadListener {
        override fun onImageLoaded() {
            imageLoaded.value = true
        }

        override fun onImageFailedToLoad() {
            imageLoaded.value = false
        }

    }
    val requestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false;
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return true;
        }
    }
}