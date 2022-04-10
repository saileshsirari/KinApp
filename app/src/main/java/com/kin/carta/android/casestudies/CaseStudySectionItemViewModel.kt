
package com.kin.carta.android.casestudies

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kin.carta.android.adapters.ImageLoadListener
import com.kin.carta.android.data.BodyElement

class CaseStudySectionItemViewModel(
    val sectionTitle: String?,
    val bodyElement: BodyElement
){
    val imageLoaded = MutableLiveData<Boolean>(false)

    val listener = object : ImageLoadListener {
        override fun onImageLoaded() {
            imageLoaded.value =true
        }

        override fun onImageFailedToLoad() {
            imageLoaded.value =false
        }

    }

    val requestListener  = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            imageLoaded.value =false
            return false;
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            imageLoaded.value =true
            return false;
        }
    }
}
