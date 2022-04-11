/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kin.carta.android

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.snackbar.Snackbar
import com.kin.carta.android.utils.EMPTY_STRING

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

fun Boolean.toVisibility(hiddenCase: Int = View.GONE): Int {
    return if (this) View.VISIBLE else hiddenCase
}

const val SNACK_BAR_DEFAULT_POSITION = Gravity.TOP.or(Gravity.CENTER_HORIZONTAL)
