@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.data.datasource.PostCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.PostRemoteDataSource
import com.sanogueralorenzo.sample.post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostRepositoryImplTest {

    private lateinit var repository: PostRepositoryImpl

    private val mockCacheDataSource: PostCacheDataSource = mock()
    private val mockRemoteDataSource: PostRemoteDataSource = mock()

    private val postId = post.id

    private val cacheItem = post.copy(title = "cache")
    private val remoteItem = post.copy(title = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val cacheThrowable = Throwable()
    private val remoteThrowable = Throwable()

    @Before
    fun setUp() {
        repository = PostRepositoryImpl(mockCacheDataSource, mockRemoteDataSource)
    }

    @Test
    fun `get posts cache success`() {
        // given
        whenever(mockCacheDataSource.get()).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCacheDataSource).get()
        test.assertValue(cacheList)
    }

    @Test
    fun `get posts cache fail fallback remote succeeds`() {
        // given
        whenever(mockCacheDataSource.get()).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get()).thenReturn(Single.just(remoteList))
        whenever(mockCacheDataSource.set(remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCacheDataSource).get()
        verify(mockRemoteDataSource).get()
        verify(mockCacheDataSource).set(remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `get posts cache fail fallback remote fails`() {
        // given
        whenever(mockCacheDataSource.get()).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get()).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCacheDataSource).get()
        verify(mockRemoteDataSource).get()
        test.assertError(remoteThrowable)
    }

    @Test
    fun `get posts remote success`() {
        // given
        whenever(mockRemoteDataSource.get()).thenReturn(Single.just(remoteList))
        whenever(mockCacheDataSource.set(remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockRemoteDataSource).get()
        verify(mockCacheDataSource).set(remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `get posts remote fail`() {
        // given
        whenever(mockRemoteDataSource.get()).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockRemoteDataSource).get()
        test.assertError(remoteThrowable)
    }

    @Test
    fun `get post cache success`() {
        // given
        whenever(mockCacheDataSource.get(postId)).thenReturn(Single.just(cacheItem))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCacheDataSource).get(postId)
        test.assertValue(cacheItem)
    }

    @Test
    fun `get post cache fail fallback remote succeeds`() {
        // given
        whenever(mockCacheDataSource.get(postId)).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get(postId)).thenReturn(Single.just(remoteItem))
        whenever(mockCacheDataSource.set(remoteItem)).thenReturn(Single.just(remoteItem))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCacheDataSource).get(postId)
        verify(mockRemoteDataSource).get(postId)
        verify(mockCacheDataSource).set(remoteItem)
        test.assertValue(remoteItem)
    }

    @Test
    fun `get post cache fail fallback remote fails`() {
        // given
        whenever(mockCacheDataSource.get(postId)).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get(postId)).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCacheDataSource).get(postId)
        verify(mockRemoteDataSource).get(postId)
        test.assertError(remoteThrowable)
    }

    @Test
    fun `get post remote success`() {
        // given
        whenever(mockRemoteDataSource.get(postId)).thenReturn(Single.just(remoteItem))
        whenever(mockCacheDataSource.set(remoteItem)).thenReturn(Single.just(remoteItem))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockRemoteDataSource).get(postId)
        verify(mockCacheDataSource).set(remoteItem)
        test.assertValue(remoteItem)
    }

    @Test
    fun `get post remote fail`() {
        // given
        whenever(mockRemoteDataSource.get()).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockRemoteDataSource).get()
        test.assertError(remoteThrowable)
    }
}
