package com.kin.carta.android

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import com.google.android.material.snackbar.Snackbar

fun Int?.safe(): Int {
    if (this == null)
        return 0

    return this
}

fun Exception.print() {
    if (BuildConfig.DEBUG) {
        printStackTrace()
    }
}

fun ViewGroup.swallowTouches() {
    isClickable = true
    isFocusable = false
    descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
}

fun Activity.showSnackBar(
    createView: (snackBar: Snackbar, snackBarLayout: Snackbar.SnackbarLayout) -> View,
    length: Int = Snackbar.LENGTH_LONG,
    position: Int = SNACK_BAR_DEFAULT_POSITION,
): Snackbar {
    val snackBar: Snackbar = Snackbar.make(
        findViewById(android.R.id.content),
        EMPTY_STRING,
        length
    )
    val snackBarView = snackBar.view as Snackbar.SnackbarLayout
    snackBarView.updateLayoutParams<FrameLayout.LayoutParams> {
        gravity = position
    }
    snackBarView.clipChildren = false
    snackBarView.clipToPadding = false
    snackBarView.setBackgroundColor(Color.TRANSPARENT)
    snackBarView.addView(createView(snackBar, snackBarView), 0)
    snackBar.show()
    return snackBar
}

fun TextView.setOrHide(text: String?, hiddenCase: Int = View.GONE) {
    this.text = text
    visibility = text.isNullOrBlank().toVisibilityHidden(hiddenCase)
}

fun Boolean.toVisibility(hiddenCase: Int = View.GONE): Int {
    return if (this) View.VISIBLE else hiddenCase
}

fun Boolean.toVisibilityHidden(hiddenCase: Int = View.GONE): Int {
    return not().toVisibility(hiddenCase)
}

const val SNACK_BAR_DEFAULT_POSITION = Gravity.TOP.or(Gravity.CENTER_HORIZONTAL)

