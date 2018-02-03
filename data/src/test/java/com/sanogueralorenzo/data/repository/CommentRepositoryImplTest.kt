@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createComment
import com.sanogueralorenzo.data.createCommentEntity
import com.sanogueralorenzo.data.model.CommentEntity
import com.sanogueralorenzo.data.model.CommentMapper
import com.sanogueralorenzo.data.remote.CommentsApi
import io.reactivex.Completable
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

    private val postId = "1"
    private val key = "Comment List"

    private val commentEntityList = listOf(createCommentEntity())
    private val commentList = listOf(createComment())

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get comments success`() {
        // given
        _when(mockApi.getComments(postId)).thenReturn(Single.just(commentEntityList))
        _when(mockCache.load(postId, emptyList())).thenReturn(Single.just(commentEntityList))
        _when(mockCache.save(key, commentEntityList)).thenReturn(Completable.complete())

        // when
        val test = repository.getComments(postId).test()

        // then
        verify(mockApi).getComments(postId)
        verify(mockCache).load(key, emptyList())
        verify(mockCache).save(key + postId, commentEntityList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValue(commentList)
    }

    @Test
    fun `get comments fail`() {
        // given
        val throwable = Throwable()
        _when(mockApi.getComments(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.getComments(postId).test()

        // then
        verify(mockApi).getComments(postId)
        verify(mockCache).load(key, emptyList())
        verify(mockCache, never()).save(key + postId, commentEntityList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValue(commentList)
    }
}
