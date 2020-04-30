package com.sanogueralorenzo.navigation.core

import android.os.Bundle
import android.os.Parcelable
import com.airbnb.mvrx.MvRx

internal fun Parcelable?.toMvRxBundle(): Bundle? =
    this?.let { Bundle().also { it.putParcelable(MvRx.KEY_ARG, it) } }
