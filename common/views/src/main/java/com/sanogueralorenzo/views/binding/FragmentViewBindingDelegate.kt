package com.sanogueralorenzo.views.binding

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.doOnDetach
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    val fragment: Fragment,
) : ReadOnlyProperty<Fragment, T> {
    private val clearBindingHandler by lazy(LazyThreadSafetyMode.NONE) { Handler(Looper.getMainLooper()) }
    private var binding: T? = null

    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(
                object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        val bindingToCleanup = binding ?: return
                        // ensures binding is only cleared after detached
                        clearBindingHandler.post {
                            bindingToCleanup.root.doOnDetach {
                                if (binding === bindingToCleanup) {
                                    binding = null
                                }
                            }
                        }
                    }
                },
            )
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        // onCreateView may be called between onDestroyView and next Main thread cycle.
        // In this case [binding] refers to the previous fragment view.
        // Check that binding's root view matches current fragment view
        if (thisRef.view != null && binding?.root !== thisRef.view) {
            binding = null
        }
        binding?.let { return it }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }

        binding = bindMethod.invoke(null, thisRef.requireView())!!.cast<T>()
        return binding!!
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> Any.cast(): T = this as T
}

/**
 * Create bindings for a fragment.
 *
 * To use, just call inside your fragment:
 * private val binding: ExampleFragmentBinding by viewBinding()
 *
 * Binding name is generated from the layout file, example_fragment.xml -> ExampleFragmentBinding
 *
 * Fragments still need to have the layout passed through the constructor for inflation
 */
inline fun <reified T : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate(T::class.java, this)
