package com.kin.carta.android

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A lazy property that gets cleaned up when the fragment/Activity is destroyed.
 *
 * Accessing this variable in a destroyed fragment will throw NPE.
 */
class AutoClearedValue<T : Any>(val lifecycleOwner: LifecycleOwner) :
    ReadWriteProperty<LifecycleOwner, T>, DefaultLifecycleObserver {
    private var _value: T? = null

    init {
        //In-case of Fragment, viewLifecycle is observed
        if (lifecycleOwner is Fragment) {
            lifecycleOwner.viewLifecycleOwnerLiveData.observe(lifecycleOwner) { newViewLifecycleOwner ->
                newViewLifecycleOwner?.lifecycle?.let {
                    observeLifecycle(it)
                }
            }
        } else {
            observeLifecycle(lifecycleOwner.lifecycle)
        }
    }

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: LifecycleOwner, property: KProperty<*>, value: T) {
        _value = value
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        _value = null
    }

    private fun observeLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }
}

/**
 * Creates an [AutoClearedValue] associated with lifecycleOwner
 */
fun <T : Any> LifecycleOwner.autoCleared() = AutoClearedValue<T>(this)
