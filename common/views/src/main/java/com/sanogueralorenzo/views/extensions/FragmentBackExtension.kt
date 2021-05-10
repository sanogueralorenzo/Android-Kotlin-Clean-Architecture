package com.sanogueralorenzo.views.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Extension to handle back pressed on fragments
 * @param callback -> the callback to execute on back pressed
 * @param predicate -> condition that needs to be met in order to execute back press
 *
 * If predicate not met, back press is propagated to activity
 */
fun Fragment.onFragmentBackCallback(
    callback: () -> Unit,
    predicate: () -> Boolean = { true }
) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                predicate() -> callback()
                else -> {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        }
    })
}
