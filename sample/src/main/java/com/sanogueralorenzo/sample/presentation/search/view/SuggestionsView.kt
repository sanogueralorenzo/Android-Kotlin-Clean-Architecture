package com.sanogueralorenzo.sample.presentation.search.view

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sanogueralorenzo.sample.presentation.search.DisplayMode

class SuggestionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {

    private val chipGroup: ChipGroup = ChipGroup(context)
        .also { it.isSingleLine = true }

    init {
        isHorizontalScrollBarEnabled = false
        addView(chipGroup)
    }

    fun addSuggestions(
        suggestions: List<DisplayMode>,
        onTrendingClick: () -> Unit,
        onSearchTermClick: (String) -> Unit
    ) {
        chipGroup.removeAllViews()
        suggestions.map { suggestion ->
            Chip(context).also { chip ->
                chip.text = suggestion.toString()
                when (suggestion) {
                    DisplayMode.Trending -> chip.setOnClickListener { onTrendingClick() }
                    is DisplayMode.Search -> chip.setOnClickListener { onSearchTermClick(suggestion.search) }
                }
                chipGroup.addView(chip)
            }
        }
    }

    companion object {
        /**
         * TODO Bad approach, move to local data source & translate
         * Suggestions should come from either local and/or remote data sources
         * Reason:
         * Code would be easier to update if product evolves to add/delete local suggestions
         * It could even be combined at the repository level with popular suggestions from remote
         */
        fun createSuggestions(): List<DisplayMode> = listOf(
            DisplayMode.Trending,
            DisplayMode.Search("Funny"),
            DisplayMode.Search("Sad"),
            DisplayMode.Search("Dog"),
            DisplayMode.Search("Love"),
            DisplayMode.Search("Cat")
        )
    }
}
