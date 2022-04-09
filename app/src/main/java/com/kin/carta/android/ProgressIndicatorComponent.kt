package com.kin.carta.android

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.kin.carta.android.databinding.LayoutProgressIndicatorComponentBinding

class ProgressIndicatorComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val viewBinding =
        LayoutProgressIndicatorComponentBinding.inflate(LayoutInflater.from(context), this,false)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ProgressIndicatorComponent).use {
            setMessage(it.getString(R.styleable.ProgressIndicatorComponent_message))
            setBackgroundDimEnabled(
                it.getBoolean(
                    R.styleable.ProgressIndicatorComponent_android_backgroundDimEnabled,
                    true
                )
            )
        }
        swallowTouches()
    }

    fun setMessage(message: String?) {
        viewBinding.tvMessage.setOrHide(message)
    }

    fun setBackgroundDimEnabled(enabled: Boolean) {
        if (enabled) {
            setBackgroundColor(Color.BLACK)
        } else {
            background = null
        }
    }
}