package com.google.samples.apps.sunflower

interface ProgressDialogInterface {
    fun showProgressDialog(message: String? = null)
    fun hideProgressDialog()
}