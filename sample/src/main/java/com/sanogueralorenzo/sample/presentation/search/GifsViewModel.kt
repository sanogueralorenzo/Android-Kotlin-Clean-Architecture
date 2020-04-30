package com.sanogueralorenzo.sample.presentation.search

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.sanogueralorenzo.sample.domain.SearchGifsUseCase
import com.sanogueralorenzo.sample.domain.TrendingGifsUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class GifsViewModel @AssistedInject constructor(
    @Assisted state: GifsState,
    private val searchUseCase: SearchGifsUseCase,
    private val trendingUseCase: TrendingGifsUseCase
) : BaseMvRxViewModel<GifsState>(state) {

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
        searchUseCase(search, offset).execute {
            copy(
                request = it,
                items = combinePaginationItems(offset, DisplayMode.Search(search), it),
                displayMode = DisplayMode.Search(search)
            )
        }
    }

    fun trending(offset: Int = 0) {
        trendingUseCase(offset).execute {
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

    @AssistedInject.Factory
    interface Factory {
        fun create(state: GifsState): GifsViewModel
    }

    companion object : MvRxViewModelFactory<GifsViewModel, GifsState> {
        override fun create(viewModelContext: ViewModelContext, state: GifsState): GifsViewModel? =
            (viewModelContext as FragmentViewModelContext)
                .fragment<GifsFragment>()
                .viewModelFactory.create(state)
    }
}
