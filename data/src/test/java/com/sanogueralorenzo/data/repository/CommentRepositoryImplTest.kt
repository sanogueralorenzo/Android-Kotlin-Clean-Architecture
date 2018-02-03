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
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when

class CommentRepositoryImplTest {

    private lateinit var repository: CommentRepositoryImpl

    private val mockApi = mock<CommentsApi> {}
    private val mockCache = mock<Cache<List<CommentEntity>>>()
    private val mapper = CommentMapper()

    private val postId = "1"
    private val key = "Comment List"

    private val cacheList = listOf(createCommentEntity().copy(name = "cache"))
    private val remoteList = listOf(createCommentEntity().copy(name = "remote"))

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get comments success`() {
        // given
        _when(mockCache.load(key + postId, emptyList())).thenReturn(Single.just(cacheList))
        _when(mockApi.getComments(postId)).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key + postId, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.getComments(postId).test()

        // then
        verify(mockCache).load(key + postId, emptyList())
        verify(mockApi).getComments(postId)
        verify(mockCache).save(key + postId, remoteList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValues(mapper.mapToDomain(cacheList), mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get comments fail`() {
        // given
        val throwable = Throwable()
        _when(mockCache.load(key + postId, emptyList())).thenReturn(Single.just(emptyList()))
        _when(mockApi.getComments(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.getComments(postId).test()

        // then
        verify(mockApi).getComments(postId)
        verify(mockCache).load(key + postId, emptyList())
        verify(mockCache, never()).save(ArgumentMatchers.anyString(), ArgumentMatchers.anyList())

        test.assertError(throwable)
        test.assertValueCount(1)
        test.assertNotComplete()
        test.assertValue(emptyList())
    }
}
