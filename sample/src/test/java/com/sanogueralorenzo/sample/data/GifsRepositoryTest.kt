package com.sanogueralorenzo.sample.data

import com.sanogueralorenzo.sample.datasource.remote.GifsRemoteDataSource
import com.sanogueralorenzo.sample.domain.Gif
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
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
    fun `repository get trending gifs calls and returns expected gifs`() {
        every { mockRemoteDataSource.loadTrending(offset) } returns Single.just(mockResult)

        val test = repository.loadTrending(offset).test()

        verify { mockRemoteDataSource.loadTrending(offset) }
        test.assertValue(mockResult)
    }

    @Test
    fun `repository search calls with the expected search term and returns expected gifs`() {
        val searchTerm = "dog"
        every {
            mockRemoteDataSource.search(
                searchTerm,
                offset
            )
        } returns Single.just(mockResult)

        val test = repository.search(searchTerm, offset).test()

        verify { mockRemoteDataSource.search(searchTerm, offset) }
        test.assertValue(mockResult)
    }

    // TODO Test errors if we were converting them
}