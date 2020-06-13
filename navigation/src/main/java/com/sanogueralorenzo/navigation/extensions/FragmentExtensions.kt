package com.sanogueralorenzo.navigation.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.replaceFragment(
    fragment: Fragment,
    backstack: Boolean = true,
    containerId: Int = android.R.id.content
) = activity!!.replaceFragment(fragment, backstack, containerId)

fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    backstack: Boolean = true,
    containerId: Int = android.R.id.content
) = supportFragmentManager
    .beginTransaction()
    .replace(containerId, fragment)
    .also { if (backstack) it.addToBackStack(null) }
    .commit()
