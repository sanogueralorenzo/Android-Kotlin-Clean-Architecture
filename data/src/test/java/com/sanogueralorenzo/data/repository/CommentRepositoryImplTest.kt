@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createCommentEntity
import com.sanogueralorenzo.data.model.CommentEntity
import com.sanogueralorenzo.data.model.CommentMapper
import com.sanogueralorenzo.data.remote.CommentsApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when

class CommentRepositoryImplTest {

    private lateinit var repository: CommentRepositoryImpl

    private val mockApi = mock<CommentsApi> {}
    private val mockCache = mock<Cache<List<CommentEntity>>>()
    private val mapper = CommentMapper()

    private val key = "Comment List"

    private val postId = "1"

    private val cacheItem = createCommentEntity().copy(name = "cache")
    private val remoteItem = createCommentEntity().copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get comments cache success`() {
        // given
        _when(mockCache.load(key + postId)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache).load(key + postId)
        test.assertValue(mapper.mapToDomain(cacheList))
    }

    @Test
    fun `get comments cache fail fallback remote succeeds`() {
        // given
        _when(mockCache.load(key + postId)).thenReturn(Single.error(throwable))
        _when(mockApi.getComments(postId)).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key + postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache).load(key + postId)
        verify(mockCache).save(key + postId, remoteList)
        verify(mockApi).getComments(postId)
        test.assertValue(mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get comments remote success`() {
        // given
        _when(mockApi.getComments(postId)).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key + postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getComments(postId)
        verify(mockCache).save(key + postId, remoteList)
        test.assertValue(mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get comments remote fail`() {
        // given
        _when(mockApi.getComments(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getComments(postId)
        test.assertError(throwable)
    }
}
