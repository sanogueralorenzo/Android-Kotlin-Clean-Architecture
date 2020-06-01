package com.sanogueralorenzo.navigation.core

import android.os.Bundle
import android.os.Parcelable
import com.airbnb.mvrx.MvRx

/**
 * Navigation with arguments
 *
 * Scenario 1 (MvRx screens): Send primitive types or Parcelable objects
 * MvRx will automatically set it up in the initial state of your ViewModel
 * Just remember to not have a default value and a secondary constructor (examples below)
 *
 * Primitive type example
 * Sending:
 * ExampleNavigator.fragment("com.example.ExampleFragment, "Hello")
 *
 * Receiving it:
 * Example:
 * data class ExampleState(val example: String) : MvRxState {
 *
 * constructor(exampleArg: String) : this(example = exampleArg)
 * }
 *
 * Parcelize Example (use it when more than one arg):
 * @Parcelize
 * data class ExampleArgs(val name: String, val age: Int) : Parcelable
 *
 * Sending:
 * ExampleNavigator.fragment("com.example.ExampleFragment, ExampleArgs("Mario", 28)
 *
 * Receiving it:
 * Example:
 * data class ExampleState(val exampleArgs: ExampleArgs) : MvRxState {
 *
 * constructor(args: ExampleArgs) : this(exampleArgs = args)
 * }
 */
internal fun Any.toBundle(): Bundle = if (this is Bundle) this else this.toMvRxBundle()

@Suppress("UNCHECKED_CAST")
private fun Any.toMvRxBundle(): Bundle = Bundle().also {
    when {
        this is String -> it.putString(MvRx.KEY_ARG, this)
        this is Long -> it.putLong(MvRx.KEY_ARG, this)
        this is Boolean -> it.putBoolean(MvRx.KEY_ARG, this)
        this is Int -> it.putInt(MvRx.KEY_ARG, this)
        this is Parcelable -> it.putParcelable(MvRx.KEY_ARG, this)
        this is List<*> && this.isNotEmpty() && this.first() is String ->
            it.putStringArrayList(MvRx.KEY_ARG, this as ArrayList<String>)
        else -> throw IllegalArgumentException("Non implemented simpleMvRxArg")
    }
}
