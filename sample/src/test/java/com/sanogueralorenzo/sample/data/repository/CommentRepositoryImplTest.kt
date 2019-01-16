@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.comment
import com.sanogueralorenzo.sample.data.datasource.CommentCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.CommentRemoteDataSource
import com.sanogueralorenzo.sample.post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class CommentRepositoryImplTest {

    private lateinit var repository: CommentRepositoryImpl

    private val mockCacheDataSource: CommentCacheDataSource = mock()
    private val mockRemoteDataSource: CommentRemoteDataSource = mock()

    private val postId = post.id

    private val cacheItem = comment.copy(name = "cache")
    private val remoteItem = comment.copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(mockCacheDataSource, mockRemoteDataSource)
    }

    @Test
    fun `get comments cache success`() {
        // given
        whenever(mockCacheDataSource.get(postId)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCacheDataSource).get(postId)
        test.assertValue(cacheList)
    }

    @Test
    fun `get comments cache fail fallback remote succeeds`() {
        // given
        whenever(mockCacheDataSource.get(postId)).thenReturn(Single.error(throwable))
        whenever(mockRemoteDataSource.get(postId)).thenReturn(Single.just(remoteList))
        whenever(mockCacheDataSource.set(postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCacheDataSource).get(postId)
        verify(mockRemoteDataSource).get(postId)
        verify(mockCacheDataSource).set(postId, remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `get comments remote success`() {
        // given
        whenever(mockRemoteDataSource.get(postId)).thenReturn(Single.just(remoteList))
        whenever(mockCacheDataSource.set(postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockRemoteDataSource).get(postId)
        verify(mockCacheDataSource).set(postId, remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `get comments remote fail`() {
        // given
        whenever(mockRemoteDataSource.get(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockRemoteDataSource).get(postId)
        test.assertError(throwable)
    }
}
