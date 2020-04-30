package com.sanogueralorenzo.sample.domain

import com.sanogueralorenzo.sample.data.GifsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class SearchGifsUseCaseTest {
    private lateinit var usecase: SearchGifsUseCase
    private val mockRepository: GifsRepository = mockk()

    private val mockResult = mockk<List<Gif>>(relaxed = true)

    private val offset = 20

    @Before
    fun setUp() {
        usecase = SearchGifsUseCase(mockRepository)
    }

    @Test
    fun `usecase calls search from repository with parameter search term and returns expected result`() {
        val searchTerm = "dog"
        every { mockRepository.search(searchTerm, offset) } returns Single.just(mockResult)

        val test = usecase(searchTerm, offset).test()

        verify { mockRepository.search(searchTerm, offset) }
        test.assertValue(mockResult)
    }

    //  TODO Test error flow if converting it
}