package com.sanogueralorenzo.sample.presentation.search

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Incomplete
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized
import com.sanogueralorenzo.sample.domain.Gif
import com.sanogueralorenzo.sample.presentation.search.view.SuggestionsView

/**
 * PersistState annotation saves into viewmodel store owner & properly survives process restart
 * As MvRx documentation states:
 *
 * You should ONLY SAVE what you need to refetch data, not fetched data itself.
 * For example, for search, save the search filters not the search results.
 */
data class GifsState(
    val request: Async<List<Gif>> = Uninitialized,
    val items: List<Gif> = emptyList(),
    @PersistState val displayMode: DisplayMode = DisplayMode.Trending,
    val suggestions: List<DisplayMode> = SuggestionsView.createSuggestions()
) : MavericksState {

    val isLoading: Boolean
        get() = request is Incomplete

    /**
     * Combines existing pagination with new request if they are the same display mode (search term or trending)
     * Otherwise just returns new request
     *
     * Error scenarios:
     * Error during scrolling (pagination) -> Previous items are kept
     * Error during new user DisplayMode (search or trending) -> Previous items are NOT kept
     */
    fun combinePaginationItems(
        offset: Int,
        displayMode: DisplayMode,
        newRequestItems: Async<List<Gif>>
    ): List<Gif> =
        when {
            offset != 0 && this.displayMode == displayMode -> this.items
            else -> emptyList()
        } + (newRequestItems() ?: emptyList())
}
