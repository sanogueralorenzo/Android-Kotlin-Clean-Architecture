package com.sanogueralorenzo.sample.data

import com.sanogueralorenzo.sample.datasource.remote.GifsRemoteDataSource
import com.sanogueralorenzo.sample.domain.Gif
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GifsRepositoryTest {
    private lateinit var repository: GifsRepository

    private val mockResult = mockk<List<Gif>>(relaxed = true)

    private val mockRemoteDataSource: GifsRemoteDataSource = mockk()

    private val offset = 20

    @Before
    fun setUp() {
        repository = GifsRepository(mockRemoteDataSource)
    }

    @Test
    fun `repository get trending gifs calls and returns expected gifs`() = runBlocking {
        coEvery { mockRemoteDataSource.loadTrending(offset) } returns mockResult

        val test = repository.loadTrending(offset)

        coVerify { mockRemoteDataSource.loadTrending(offset) }
        assert(test == mockResult)
    }

    @Test
    fun `repository search calls with the expected search term and returns expected gifs`() = runBlocking {
        val searchTerm = "dog"
        coEvery { mockRemoteDataSource.search(searchTerm, offset) } returns mockResult

        val test = repository.search(searchTerm, offset)

        coVerify { mockRemoteDataSource.search(searchTerm, offset) }
        assert(test ==  mockResult)
    }

    // TODO Test errors if we were converting them
}