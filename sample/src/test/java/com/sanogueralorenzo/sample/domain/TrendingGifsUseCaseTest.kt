package com.sanogueralorenzo.sample.domain

import com.sanogueralorenzo.sample.data.GifsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
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
    fun `usecase calls trending from repository and returns expected result`() {
        every { mockRepository.loadTrending(offset) } returns Single.just(mockResult)

        val test = usecase(offset).test()

        verify { mockRepository.loadTrending(offset) }
        test.assertValue(mockResult)
    }
}