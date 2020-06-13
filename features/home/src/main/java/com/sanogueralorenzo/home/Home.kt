package com.sanogueralorenzo.home

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.ViewModelContext
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController
import io.reactivex.Single

/**
 * @see com.sanogueralorenzo.navigation.features.HomeNavigation.homeFragment
 */
@Suppress("Unused")
class HomeFragment : ContainerFragment() {
    private val viewModel: HomeViewModel by fragmentViewModel(HomeViewModel::class)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener { viewModel.onSwipeAction() }
    }

    override fun controller() = simpleController(viewModel) { state -> }

    override fun invalidate() {
        super.invalidate()
        withState(viewModel) { state -> swipeRefreshLayout.isRefreshing = state.feedItems.complete.not() }
    }
}

class HomeViewModel(
    state: HomeState
) : BaseMvRxViewModel<HomeState>(state) {

    fun onSwipeAction() {
        Single.just(emptyList<HomeItem>()).execute { copy(feedItems = it) }
    }

    companion object : MvRxViewModelFactory<HomeViewModel, HomeState> {

        override fun create(
            viewModelContext: ViewModelContext,
            state: HomeState
        ): HomeViewModel = HomeViewModel(state)
    }
}

data class HomeState(
    val feedItems: Async<List<HomeItem>> = Uninitialized
) : MvRxState

sealed class HomeItem
