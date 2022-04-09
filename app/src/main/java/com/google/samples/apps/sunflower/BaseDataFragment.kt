package com.google.samples.apps.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseDataFragment<B : ViewDataBinding, V : BaseViewModel>(layoutResId: Int?) :
    BaseFragment<B>(layoutResId) {
    @get:LayoutRes
    open val bindingVariable: Int = BR.viewModel

    val viewModel: V by lazy { ViewModelProvider(this)[viewModelClass] }

    protected abstract val viewModelClass: Class<V>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewBinding.setVariable(bindingVariable, viewModel)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onObserveProgressDialogData()
    }

    internal open fun onObserveProgressDialogData() {
        viewModel.progressDialog.observe(viewLifecycleOwner) {
            onProgressDialogDataUpdate(it == true)
        }
    }

    internal open fun onProgressDialogDataUpdate(visible: Boolean) {
        setProgressDialog(visible)
    }

    fun progressDialog(visible: Boolean) {
        viewModel.progressDialog.value = visible
    }
}