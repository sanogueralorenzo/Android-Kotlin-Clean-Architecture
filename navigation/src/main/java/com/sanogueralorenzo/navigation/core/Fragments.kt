package com.sanogueralorenzo.navigation.core

import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.Fragment

/**
 * Usage: Create an "object" implementation to hold the fragments that live in another directory.
 * The fully qualified fragment name is provided as a string.
 * The fragment is later looked up with reflection.
 * This enables fragments to be used when a feature doesn't explicitly depend on them.
 */
open class Fragments(private val packagePrefix: String) {

    /** Combine package name and fragment name.
     *  Without assuming that they have a period in the correct place at beginning or end.
     */
    @PublishedApi
    internal fun fqn(name: String) = "${packagePrefix.removeSuffix(".")}.${name.removePrefix(".")}"

    /**
     * Creates an intent that will be started with this MvRxFragment.
     * In debug builds if the MvRx fragment is missing a fragment that automatically
     * finishes and toasts the missing fragment will be shown.
     */
    internal fun fragment(name: String, arg: Parcelable? = null): Fragment =
        loadFragment(fqn(name), arg.toMvRxBundle())

    /**
     * Creates an intent that will be started with this MvRxFragment.
     * In debug builds if the MvRx fragment is missing a fragment that automatically
     * finishes and toasts the missing fragment will be shown.
     */
    internal fun fragmentIntent(name: String, arg: Parcelable? = null): Intent =
        ContainerActivity.newIntent(fqn(name), arg.toMvRxBundle())
}
