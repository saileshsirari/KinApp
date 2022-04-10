package com.kin.carta.android

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

/**
 * Returns current application object
 */


fun Fragment.showProgressDialog(message: String? = null) {
    progressDialogInterface()?.showProgressDialog(message)
}

fun Fragment.hideProgressDialog() {
    progressDialogInterface()?.hideProgressDialog()
}

fun Fragment.setProgressDialog(visible: Boolean, message: String? = null) {
    if (visible) {
        showProgressDialog(message)
    } else {
        hideProgressDialog()
    }
}

private fun Fragment.progressDialogInterface() = (activity as? ProgressDialogInterface)
