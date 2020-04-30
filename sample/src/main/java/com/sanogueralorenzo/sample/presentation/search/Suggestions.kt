package com.sanogueralorenzo.sample.presentation.search

/**
 * TODO Bad approach, move to local data source
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
