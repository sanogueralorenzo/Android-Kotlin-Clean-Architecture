@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.posts.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.commentEntity
import com.sanogueralorenzo.posts.data.model.CommentEntity
import com.sanogueralorenzo.posts.data.model.mapToDomain
import com.sanogueralorenzo.posts.data.remote.CommentsApi
import com.sanogueralorenzo.posts.post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class CommentRepositoryImplTest {

    private lateinit var repository: CommentRepositoryImpl

    private val mockApi: CommentsApi = mock()
    private val mockCache: Cache<List<CommentEntity>> = mock()

    private val postId = post.id

    private val cacheItem = commentEntity.copy(name = "cache")
    private val remoteItem = commentEntity.copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    val key = "Comment List"

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(mockApi, mockCache)
    }

    @Test
    fun `get comments cache success`() {
        // given
        whenever(mockCache.load(key + postId)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache).load(key + postId)
        test.assertValue(cacheList.mapToDomain())
    }

    @Test
    fun `get comments cache fail fallback remote succeeds`() {
        // given
        whenever(mockCache.load(key + postId)).thenReturn(Single.error(throwable))
        whenever(mockApi.getComments(postId)).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key + postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache).load(key + postId)
        verify(mockCache).save(key + postId, remoteList)
        verify(mockApi).getComments(postId)
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get comments remote success`() {
        // given
        whenever(mockApi.getComments(postId)).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key + postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getComments(postId)
        verify(mockCache).save(key + postId, remoteList)
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get comments remote fail`() {
        // given
        whenever(mockApi.getComments(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getComments(postId)
        test.assertError(throwable)
    }
}
