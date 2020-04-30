package com.sanogueralorenzo.sample.datasource.remote

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
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
    fun `remote data source get trending gifs calls and returns expected gifs`() {
        every { mockService.getTrendingGifs(offset) } returns Single.just(mockResult)

        val test = remoteDataSource.loadTrending(offset).test()

        verify { mockService.getTrendingGifs(offset) }
        test.assertValue { it == mockResult.toDomainModel() }
    }

    @Test
    fun `remote data source search calls with the expected search term and returns expected gifs`() {
        val searchTerm = "dog"
        every { mockService.searchGifs(searchTerm, offset) } returns Single.just(mockResult)

        val test = remoteDataSource.search(searchTerm, offset).test()

        verify { mockService.searchGifs(searchTerm, offset) }
        test.assertValue { it == mockResult.toDomainModel() }
    }

    // TODO Test errors if we were converting them
}