package com.google.samples.apps.sunflower

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * Base Fragment for all fragments
 */
abstract class BaseFragment<B : ViewDataBinding>(@get:LayoutRes val layoutResId: Int?) :
    Fragment() {

    var viewBinding by autoCleared<B>()
        private set

    @LayoutRes
    open fun getLayoutId(): Int {
        return layoutResId.safe()
    }

    private var clearOptionsMenu = false

    private var onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            //Do nothing, as disabled
        }
    }

    //Call this function in PreBinding or onCreate()
    //This will clear any options menu items in activity or added by previous fragment
    protected fun clearOptionsMenu() {
        clearOptionsMenu = true
        setHasOptionsMenu(clearOptionsMenu)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performPreBindingOperations()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<B>(
            inflater,
            getLayoutId(),
            container,
            false
        ).apply {
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPressedCallback)
        performUpdateOnViews()
        viewBinding.lifecycleOwner = viewLifecycleOwner
        performPostBindingOperations()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (clearOptionsMenu)
            menu.clear()
    }

    /**
     * Override to setup any data related code
     * Will be called once for every fragment creation
     */
    protected open fun performPreBindingOperations() {
        //should contain non-UI related code
    }

    /**
     * Override to do view related code
     * Will be called after configureToolbar, and before performPostBindingOperations
     */
    protected open fun performUpdateOnViews() {
        //override and perform any UI related code, like customize color, background etc
    }

    /**
     * Override to update views with data models
     * Will be called after performUpdateOnViews
     */
    protected open fun performPostBindingOperations() {
        //override if you have something to update with data models
    }
}