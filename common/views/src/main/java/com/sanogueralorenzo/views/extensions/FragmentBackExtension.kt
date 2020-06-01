package com.sanogueralorenzo.views.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Extension to handle back pressed on fragments
 * @param callback -> the callback to execute on back pressed
 * @param backPressHandlePredicate -> predicate that needs to be met in order to execute back press
 */
fun Fragment.onFragmentBackCallback(
    callback: () -> Unit,
    backPressHandlePredicate: () -> Boolean = { true }
) {
    activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                backPressHandlePredicate() -> callback()
                else -> {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        }
    })
}
