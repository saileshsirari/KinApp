package com.kin.carta.android

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.kin.carta.android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProgressDialogInterface {
    private var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    override fun showProgressDialog(message: String?) {
        if (progressDialog == null) {
            progressDialog = AlertDialog.Builder(this)
                .setView(R.layout.progress_indicator_layout)
                .setCancelable(false)
                .create()
            progressDialog?.window?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        this,
                        android.R.color.holo_orange_dark
                    )
                )
            )
        }
        progressDialog?.show()
    }

    override fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    override fun onDestroy() {
        hideProgressDialog()
        super.onDestroy()
    }
}
