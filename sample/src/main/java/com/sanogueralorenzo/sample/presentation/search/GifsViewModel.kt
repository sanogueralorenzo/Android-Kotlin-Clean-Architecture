package com.sanogueralorenzo.sample.presentation.search

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.sanogueralorenzo.sample.data.GifsRepository
import kotlinx.coroutines.Dispatchers

class GifsViewModel constructor(
    state: GifsState,
    private val repository: GifsRepository = GifsRepository()
) : MavericksViewModel<GifsState>(state) {

    /**
     * ViewModel initialization can be first time or after process recreation
     * On process restart, display mode might have been switched by user input (saved with Parcelize)
     */
    init {
        withState { it.loadPage() }
    }

    private fun GifsState.loadPage(offset: Int = 0) = when (displayMode) {
        is DisplayMode.Trending -> trending(offset)
        is DisplayMode.Search -> search(displayMode.search, offset)
    }

    fun search(search: String, offset: Int = 0) {
        suspend {
            repository.search(search, offset)
        }.execute(Dispatchers.IO) {
            copy(
                request = it,
                items = combinePaginationItems(offset, DisplayMode.Search(search), it),
                displayMode = DisplayMode.Search(search)
            )
        }
    }

    fun trending(offset: Int = 0) {
        suspend {
            repository.loadTrending(offset)
        }.execute {
            copy(
                request = it,
                items = combinePaginationItems(offset, DisplayMode.Trending, it),
                displayMode = DisplayMode.Trending
            )
        }
    }

    fun loadMore() = withState {
        if (it.request.complete && it.items.isNotEmpty()) {
            it.loadPage(it.items.count())
        }
    }

    fun onSwipeAction() = withState { it.loadPage() }

    companion object : MavericksViewModelFactory<GifsViewModel, GifsState> {
        override fun create(viewModelContext: ViewModelContext, state: GifsState): GifsViewModel =
            GifsViewModel(state)
    }
}
