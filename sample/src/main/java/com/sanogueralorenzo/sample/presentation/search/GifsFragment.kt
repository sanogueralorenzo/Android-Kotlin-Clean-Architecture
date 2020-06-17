package com.sanogueralorenzo.sample.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.sanogueralorenzo.navigation.extensions.replaceFragment
import com.sanogueralorenzo.sample.R
import com.sanogueralorenzo.sample.presentation.detail.GifDetailFragment
import com.sanogueralorenzo.sample.presentation.search.view.SuggestionsView
import com.sanogueralorenzo.sample.presentation.search.view.searchInput
import com.sanogueralorenzo.views.extensions.setInfiniteScrolling
import com.sanogueralorenzo.views.imageRow
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController
import com.sanogueralorenzo.views.textinput.TextInputLayoutRow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GifsFragment : ContainerFragment() {

    @Inject
    lateinit var viewModelFactory: GifsViewModel.Factory
    private val viewModel: GifsViewModel by fragmentViewModel(GifsViewModel::class)

    private var suggestionsView: SuggestionsView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initListeners()
        toolbar.title = getString(R.string.app_name)
        swipeRefreshLayout.setOnRefreshListener { viewModel.onSwipeAction() }
        suggestionsView = SuggestionsView(context!!)
        appBarLayout.addView(suggestionsView)
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, GIFS_PER_ROW)
        controller.spanCount = GIFS_PER_ROW
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        recyclerView.layoutManager = layoutManager
        recyclerView.setController(controller)
        recyclerView.setInfiniteScrolling(layoutManager) { viewModel.loadMore() }

        TextInputLayoutRow(requireContext()).also {
            it.searchInput { text -> viewModel.search(text) }
            bottomView.addView(it)
        }
    }

    private fun initListeners() {
        viewModel.asyncSubscribe(GifsState::request, onFail = { showError(it) })
        viewModel.selectSubscribe(GifsState::suggestions) { suggestions ->
            suggestionsView!!.addSuggestions(
                suggestions,
                onTrendingClick = { viewModel.trending() },
                onSearchTermClick = { viewModel.search(it) }
            )
        }
    }

    override fun invalidate() {
        super.invalidate()
        withState(viewModel) { state -> swipeRefreshLayout.isRefreshing = state.isLoading }
    }

    override fun controller() = simpleController(viewModel) { state ->
        state.items.forEach {
            imageRow {
                id(it.id)
                url(it.thumbnail)
                height(IMAGE_HEIGHT)
                imageScaleType(ImageView.ScaleType.CENTER_CROP)
                clickListener { _ -> replaceFragment(GifDetailFragment.newInstance(it.original)) }
                spanSizeOverride { _, _, _ -> 1 }
            }
        }
    }

    override fun onDestroyView() {
        suggestionsView = null
        super.onDestroyView()
    }

    private companion object {
        const val GIFS_PER_ROW = 3
        const val IMAGE_HEIGHT = 200
    }
}
