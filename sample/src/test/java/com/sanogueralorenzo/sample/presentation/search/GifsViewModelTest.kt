package com.sanogueralorenzo.sample.presentation.search

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.sanogueralorenzo.sample.domain.Gif
import com.sanogueralorenzo.sample.domain.SearchGifsUseCase
import com.sanogueralorenzo.sample.domain.TrendingGifsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
        coEvery { mockTrendingGifsUseCase.run(offset) } returns mockResult

        viewModel = createViewModel(GifsState())

        coVerify { mockTrendingGifsUseCase.run(offset) }
        withState(viewModel) { assert(it.request() == mockResult) }
    }

    @Test
    fun `process restore with search calls search usecase and updates state`() {
        val searchTerm = "dog"
        val displayMode = DisplayMode.Search(searchTerm)
        coEvery { mockSearchGifsUseCase.run(searchTerm, offset) } returns mockResult

        viewModel = createViewModel(GifsState(displayMode = displayMode))

        coVerify { mockSearchGifsUseCase.run(searchTerm, offset) }
        withState(viewModel) { assert(it.request() == mockResult) }
    }

    @Test
    fun `on trending calls trending usecase and updates state`() {
        val searchTerm = "dog"
        val displayMode = DisplayMode.Search(searchTerm)
        coEvery { mockTrendingGifsUseCase.run(offset) } returns mockResult

        viewModel = createViewModel(GifsState(displayMode = displayMode))
        viewModel.trending()

        coVerify { mockTrendingGifsUseCase.run(offset) }
        withState(viewModel) {
            assert(it.request() == mockResult)
            assert(it.displayMode == DisplayMode.Trending)
        }
    }

    @Test
    fun `on search calls search usecase and updates state`() {
        val searchTerm = "dog"
        val displayMode = DisplayMode.Search(searchTerm)
        coEvery { mockSearchGifsUseCase.run(searchTerm, offset) } returns mockResult

        viewModel = createViewModel(GifsState(displayMode = DisplayMode.Trending))
        viewModel.search(searchTerm)

        coVerify { mockSearchGifsUseCase.run(searchTerm, offset) }
        withState(viewModel) {
            assert(it.request() == mockResult)
            assert(it.displayMode == displayMode)
        }
    }
}
