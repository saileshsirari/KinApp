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


fun Fragment.navigate(navDirections: NavDirections) {
    try {
        findNavController().navigate(navDirections)
    } catch (e: IllegalStateException) {
        e.print()
    }
}

fun Fragment.navigate(navDirections: NavDirections, navOptions: NavOptions? = null) {
    try {
        findNavController().navigate(navDirections, navOptions)
    } catch (e: IllegalStateException) {
        e.print()
    }
}

/**
 * Returns current graph ID or null
 */
fun Fragment.currentGraphId(): Int? {
    return findNavController().currentDestination?.parent?.id
}

/**
 * Pop to specified destination ID
 * inclusive: set true to pop destination, false to keep the destination
 */
fun Fragment.popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false): Boolean {
    try {
        return findNavController().popBackStack(destinationId, inclusive)
    } catch (e: IllegalStateException) {
        e.print()
    }

    return false
}

/**
 * Pop current fragment from backstack
 */
fun Fragment.popBackStack(): Boolean {
    try {
        return findNavController().popBackStack()
    } catch (e: IllegalStateException) {
        e.print()
    }

    return false
}


/**
 * Checks if NavController exists for current fragment and returns true/false
 */
fun Fragment.hasNavController(): Boolean {
    try {
        findNavController()
        return true
    } catch (e: IllegalStateException) {
        e.print()
    }

    return false
}

fun Fragment.addToFragmentManager(activity: FragmentActivity?, containerId: Int, tag: String?) {
    activity?.supportFragmentManager?.beginTransaction()
        ?.add(containerId, this, tag)
        ?.commitNow()
}

fun Fragment.removeFromFragmentManager() {
    try {
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.commitNowAllowingStateLoss()
    } catch (e: IllegalStateException) {
        e.print()
    }
}
private fun Fragment.progressDialogInterface() = (activity as? ProgressDialogInterface)
