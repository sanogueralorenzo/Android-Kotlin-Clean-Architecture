package com.sanogueralorenzo.sample.presentation.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO Add Favorites feature to DisplayMode (Favorites are saved locally and have no pagination)
sealed class DisplayMode : Parcelable {
    @Parcelize
    object Trending : Parcelable, DisplayMode()

    @Parcelize
    data class Search(val search: String) : Parcelable, DisplayMode() {
        override fun toString(): String = this.search
    }

    override fun toString(): String = when (this) {
        Trending -> "Trending"
        else -> super.toString()
    }
}
