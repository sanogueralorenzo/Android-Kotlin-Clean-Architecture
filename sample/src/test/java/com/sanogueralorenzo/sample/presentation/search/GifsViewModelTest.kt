package com.sanogueralorenzo.sample.presentation.search

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.sanogueralorenzo.sample.domain.Gif
import com.sanogueralorenzo.sample.domain.SearchGifsUseCase
import com.sanogueralorenzo.sample.domain.TrendingGifsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class GifsViewModelTest {

    @get:Rule
    val mvrxRule = MvRxTestRule()

    private val mockSearchGifsUseCase = mockk<SearchGifsUseCase>(relaxed = true)
    private val mockTrendingGifsUseCase = mockk<TrendingGifsUseCase>(relaxed = true)

    private val mockResult = mockk<List<Gif>>(relaxed = true)

    private lateinit var viewModel: GifsViewModel

    private val offset = 0

    private fun createViewModel(gifsState: GifsState): GifsViewModel =
        GifsViewModel(gifsState, mockSearchGifsUseCase, mockTrendingGifsUseCase)

    @Test
    fun `initial state load calls trending usecase and updates state`() {
        every { mockTrendingGifsUseCase(offset) } returns Single.just(mockResult)

        viewModel = createViewModel(GifsState())

        verify { mockTrendingGifsUseCase(offset) }
        withState(viewModel) { assert(it.request() == mockResult) }
    }

    @Test
    fun `process restore with search calls search usecase and updates state`() {
        val searchTerm = "dog"
        val displayMode = DisplayMode.Search(searchTerm)
        every { mockSearchGifsUseCase(searchTerm, offset) } returns Single.just(mockResult)

        viewModel = createViewModel(GifsState(displayMode = displayMode))

        verify { mockSearchGifsUseCase(searchTerm, offset) }
        withState(viewModel) { assert(it.request() == mockResult) }
    }

    @Test
    fun `on trending calls trending usecase and updates state`() {
        val searchTerm = "dog"
        val displayMode = DisplayMode.Search(searchTerm)
        every { mockTrendingGifsUseCase(offset) } returns Single.just(mockResult)

        viewModel = createViewModel(GifsState(displayMode = displayMode))
        viewModel.trending()

        verify { mockTrendingGifsUseCase(offset) }
        withState(viewModel) {
            assert(it.request() == mockResult)
            assert(it.displayMode == DisplayMode.Trending)
        }
    }

    @Test
    fun `on search calls search usecase and updates state`() {
        val searchTerm = "dog"
        val displayMode = DisplayMode.Search(searchTerm)
        every { mockSearchGifsUseCase(searchTerm, offset) } returns Single.just(mockResult)

        viewModel = createViewModel(GifsState(displayMode = DisplayMode.Trending))
        viewModel.search(searchTerm)

        verify { mockSearchGifsUseCase(searchTerm, offset) }
        withState(viewModel) {
            assert(it.request() == mockResult)
            assert(it.displayMode == displayMode)
        }
    }
}
