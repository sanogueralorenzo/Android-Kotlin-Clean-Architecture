package com.sanogueralorenzo.sample.domain

import com.sanogueralorenzo.sample.data.GifsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TrendingGifsUseCaseTest {
    private lateinit var usecase: TrendingGifsUseCase
    private val mockRepository: GifsRepository = mockk()

    private val mockResult = mockk<List<Gif>>(relaxed = true)

    private val offset = 20

    @Before
    fun setUp() {
        usecase = TrendingGifsUseCase(mockRepository)
    }

    @Test
    fun `usecase calls trending from repository and returns expected result`() = runBlocking {
        coEvery { mockRepository.loadTrending(offset) } returns mockResult

        val test = usecase.run(offset)

        coVerify { mockRepository.loadTrending(offset) }
        assert(test == mockResult)
    }
}