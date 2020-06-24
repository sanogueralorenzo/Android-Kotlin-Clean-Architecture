package com.sanogueralorenzo.sample.domain

import com.sanogueralorenzo.sample.data.GifsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
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
    fun `usecase calls search from repository with parameter search term and returns expected result`() = runBlocking {
        val searchTerm = "dog"
        coEvery { mockRepository.search(searchTerm, offset) } returns mockResult

        val test = usecase.run(searchTerm, offset)

        coVerify { mockRepository.search(searchTerm, offset) }
        assert(test == mockResult)
    }

    //  TODO Test error flow if converting it
}