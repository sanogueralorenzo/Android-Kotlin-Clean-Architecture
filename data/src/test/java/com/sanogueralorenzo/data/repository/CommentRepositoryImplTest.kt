@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
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

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `repository get remote success`() {
        // given
        val entity = createCommentEntity()
        val list = listOf(entity)
        _when(mockApi.getComments(postId)).thenReturn(Single.just(list))
        _when(mockCache.save(key, list)).thenReturn(Completable.complete())

        // when
        val test = repository.getRemote(postId).test()

        // then
        verify(mockApi).getComments(postId)
        verify(mockCache).save(key + entity.id, list)

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(listOf(mapper.mapToDomain(entity)))
    }

    @Test
    fun `repository get remote fail`() {
        // given
        val throwable = Throwable()
        _when(mockApi.getComments(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.getRemote(postId).test()

        // then
        verify(mockApi).getComments(postId)

        test.assertError(throwable)
        test.assertValueCount(0)
        test.assertNotComplete()
        test.assertNoValues()
    }
}
