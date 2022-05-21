package com.sanogueralorenzo.views.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    val view: ViewGroup,
) : ReadOnlyProperty<ViewGroup, T> {
    // LayoutInflater.from() returns activity context, but we want to preserve view context in case it has DaggerComponentOwner.
    private val layoutInflater = LayoutInflater.from(view.context).cloneInContext(view.context)
    private val binding: T = try {
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.javaPrimitiveType)
        inflateMethod.invoke(null, layoutInflater, view, true)!!.cast<T>()
    } catch (e: NoSuchMethodException) {
        // <merge> tags don't have the boolean parameter.
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java)
        inflateMethod.invoke(null, layoutInflater, view)!!.cast<T>()
    }

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T = binding

    @Suppress("UNCHECKED_CAST")
    private fun <T> Any.cast(): T = this as T
}

/**
 * Create bindings for a view.
 *
 * To use, just call inside your view:
 * private val binding: ExampleViewBinding by viewBinding()
 *
 * Binding name is generated from the layout file, example_view.xml -> ExampleViewBinding
 */
inline fun <reified T : ViewBinding> ViewGroup.viewBinding() = ViewBindingDelegate(T::class.java, this)
