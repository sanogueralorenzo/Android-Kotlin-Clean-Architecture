package com.sanogueralorenzo.sample.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sanogueralorenzo.sample.R
import com.sanogueralorenzo.sample.domain.Gif
import com.sanogueralorenzo.sample.presentation.detail.GifDetailFragment
import com.sanogueralorenzo.views.extensions.replaceFragment
import com.sanogueralorenzo.views.extensions.setInfiniteScrolling
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.textinput.TextInputLayoutRow
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class GifsFragment : ContainerFragment() {

    @Inject
    lateinit var viewModelFactory: GifsViewModel.Factory

    private val viewModel: GifsViewModel by fragmentViewModel(GifsViewModel::class)

    private val itemClick: (Gif) -> Unit = {
        requireActivity().replaceFragment(GifDetailFragment.newInstance(it.original), true)
    }

    override val controller: GifsController = GifsController(itemClick)
    private var suggestionsChipGroup: ChipGroup? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initListeners()
        toolbar.title = getString(R.string.app_name)
        swipeRefreshLayout.setOnRefreshListener { viewModel.onSwipeAction() }
        suggestionsChipGroup = ChipGroup(requireContext())
            .also { it.isSingleLine = true }
        appBarLayout.addView(
            HorizontalScrollView(requireContext())
                .also {
                    it.isHorizontalScrollBarEnabled = false
                    it.addView(suggestionsChipGroup)
                }
        )
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, GIFS_PER_ROW)
        controller.spanCount = GIFS_PER_ROW
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        recyclerView.layoutManager = layoutManager
        recyclerView.setController(controller)
        recyclerView.setItemSpacingDp(SPACING)
        recyclerView.setInfiniteScrolling(layoutManager) { viewModel.loadMore() }

        TextInputLayoutRow(requireContext()).also {
            it.searchInput { text -> viewModel.search(text) }
            bottomView.addView(it)
        }
    }

    private fun initListeners() {
        viewModel.asyncSubscribe(GifsState::request, onFail = { showError(it) })
        viewModel.selectSubscribe(GifsState::suggestions) { suggestions ->
            suggestionsChipGroup!!.removeAllViews()
            suggestions.map { suggestion ->
                Chip(requireContext()).also { chip ->
                    chip.text = suggestion.toString()
                    when (suggestion) {
                        DisplayMode.Trending ->
                            chip.setOnClickListener { viewModel.trending() }
                        is DisplayMode.Search ->
                            chip.setOnClickListener { viewModel.search(suggestion.search) }
                    }
                    suggestionsChipGroup!!.addView(chip)
                }
            }
        }
    }

    override fun invalidate() =
        withState(viewModel) { state ->
            swipeRefreshLayout.isRefreshing = state.isLoading
            controller.setData(state)
        }

    override fun onDestroyView() {
        suggestionsChipGroup = null
        super.onDestroyView()
    }

    private companion object {
        const val GIFS_PER_ROW = 3
        const val SPACING = 4
    }
}
