package org.linus.base.util.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// https://medium.com/default-to-open/handling-lifecycle-with-view-binding-in-fragments-a7f237c56832
fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
    object: ReadWriteProperty<Fragment, T>, LifecycleObserver, LifecycleEventObserver {
        //A backing property to hold our value
        private var binding:T? = null
        private var viewLifecycleOwner: LifecycleOwner? = null
        init {
            //Observe the view lifecycle of the Fragment
            this@viewLifecycle.viewLifecycleOwnerLiveData
                .observe(this@viewLifecycle, Observer { newLifecycleOwner ->
                    viewLifecycleOwner?.lifecycle?.removeObserver(this)
                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                })
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            // Clear out backing property just before onDestroy, avoid memory leaks
            if (event == Lifecycle.Event.ON_DESTROY) {
                binding = null
            }
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            return this.binding!!
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            this.binding = value
        }

    }