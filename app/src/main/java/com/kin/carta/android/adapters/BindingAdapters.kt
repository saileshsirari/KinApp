package com.kin.carta.android.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kin.carta.android.R
import com.kin.carta.android.toVisibility

@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.visibility = isVisible.toVisibility(View.INVISIBLE)
}

@BindingAdapter("isGone")
fun isGone(view: View, isGone: Boolean) {
    view.visibility = (!isGone).toVisibility()
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

interface ImageLoadListener {
    fun onImageLoaded()
    fun onImageFailedToLoad()
}

@BindingAdapter("imageFromUrl", "requestListener")
fun bindImageFromUrl(view: ImageView, imageUrl: String?, requestListener: ImageLoadListener) {
    if (!imageUrl.isNullOrEmpty()) {
        // view.visibility =View.VISIBLE
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    requestListener.onImageFailedToLoad()
                    // view.visibility =View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageDrawable(resource)
                    //   view.visibility =View.VISIBLE
                    requestListener.onImageLoaded()
                    return true
                }
            }
            )


            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
