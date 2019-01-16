@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.datasource.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.commentEntity
import com.sanogueralorenzo.sample.datasource.model.mapToDomain
import com.sanogueralorenzo.sample.post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class CommentRemoteDataSourceImplTest {

    private lateinit var dataSource: CommentRemoteDataSourceImpl

    private val mockApi: CommentsApi = mock()

    private val postId = post.id

    private val remoteItem = commentEntity.copy(name = "remote")

    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        dataSource = CommentRemoteDataSourceImpl(mockApi)
    }

    @Test
    fun `get comments remote success`() {
        // given
        whenever(mockApi.getComments(postId)).thenReturn(Single.just(remoteList))

        // when
        val test = dataSource.get(postId).test()

        // then
        verify(mockApi).getComments(postId)
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get comments remote fail`() {
        // given
        whenever(mockApi.getComments(postId)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get(postId).test()

        // then
        verify(mockApi).getComments(postId)
        test.assertError(throwable)
    }
}
