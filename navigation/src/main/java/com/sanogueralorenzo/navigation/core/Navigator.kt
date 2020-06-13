package com.sanogueralorenzo.navigation.core

import android.content.Intent
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

/**
 * Split your navigation by features.
 * Each feature should be its own Kotlin Object that extends this class.
 * Big Feature objects can indicate that a feature could be split into 2 (if it makes sense).
 *
 * There are 2 types of Navigation with this centralized navigation approach:
 *
 * Local Navigation:
 * To be used when navigating within the same feature module
 * Use by sending Fragment class as a parameter ExampleFragment::class
 * Allows safer navigation and IDE navigation
 *
 * @see localFragment
 * @see localFragmentIntent
 *
 * External Navigation:
 * To be used when navigating between different feature modules (entry points)
 * Use by sending the full class name com.example.feature.ExampleFragment
 * Allows navigation between different features of your app without creating dependencies
 * Allows the use of Load/Unload modules from the IDE during a specific feature development
 * Compiling and launching only the feature development reduces clean builds to 20 sec
 *
 * @see fragment
 * @see fragmentIntent
 *
 * Requires Unit testing on String vs Canonical name to prevent bugs on name refactoring or class moving
 */
open class Navigator(private val packageName: String = "com.sanogueralorenzo.namingishard") {

    // Fragment creation between different modules --> "com.example.ExampleFragment"
    internal fun fragment(name: String, arg: Any? = null): Fragment =
        ClassRegistry.loadFragment(name, arg?.toBundle())

    // Fragment as an Intent between different modules (Container Activity) --> "com.example.ExampleFragment"
    internal fun fragmentIntent(name: String, arg: Any? = null): Intent =
        ClassRegistry.loadIntent(packageName, ContainerActivity::class.java.canonicalName!!)
            .putExtra(ContainerActivity.FRAGMENT_NAME, name)
            .putExtra(ContainerActivity.FRAGMENT_BUNDLE, arg?.toBundle())

    // Fragment creation within same feature module --> ExampleFragment::class
    fun fragment(clazz: KClass<*>, arg: Any? = null): Fragment =
        fragment(clazz.qualifiedName!!, arg)

    // Fragment as an Intent (Container Activity within same feature module) --> ExampleFragment::class
    fun fragmentIntent(clazz: KClass<*>, arg: Any? = null): Intent =
        fragmentIntent(clazz.qualifiedName!!, arg)
}
