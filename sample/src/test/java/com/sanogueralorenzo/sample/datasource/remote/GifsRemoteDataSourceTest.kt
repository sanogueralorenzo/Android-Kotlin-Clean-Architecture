package com.sanogueralorenzo.sample.datasource.remote

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GifsRemoteDataSourceTest {
    private lateinit var remoteDataSource: GifsRemoteDataSource

    private val mockResult = mockk<GifsResponse>(relaxed = true)

    private val mockService: GifService = mockk()

    private val offset = 20

    @Before
    fun setUp() {
        remoteDataSource = GifsRemoteDataSource(mockService)
    }

    @Test
    fun `remote data source get trending gifs calls and returns expected gifs`() = runBlocking {
        coEvery { mockService.getTrendingGifs(offset) } returns mockResult

        val test = remoteDataSource.loadTrending(offset)

        coVerify { mockService.getTrendingGifs(offset) }
        assert(test == mockResult.toDomainModel())
    }

    @Test
    fun `remote data source search calls with the expected search term and returns expected gifs`() = runBlocking {
        val searchTerm = "dog"
        coEvery { mockService.searchGifs(searchTerm, offset) } returns mockResult

        val test = remoteDataSource.search(searchTerm, offset)

        coVerify { mockService.searchGifs(searchTerm, offset) }
        assert(test == mockResult.toDomainModel())
    }

    // TODO Test errors if we were converting them
}