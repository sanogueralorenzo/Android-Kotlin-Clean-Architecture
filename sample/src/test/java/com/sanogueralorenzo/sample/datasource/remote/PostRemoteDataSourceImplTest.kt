@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.datasource.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.datasource.model.mapToDomain
import com.sanogueralorenzo.sample.post
import com.sanogueralorenzo.sample.postEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostRemoteDataSourceImplTest {

    private lateinit var dataSource: PostRemoteDataSourceImpl

    private val mockApi: PostsApi = mock()

    private val postId = post.id

    private val remoteItem = postEntity.copy(title = "remote")

    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        dataSource = PostRemoteDataSourceImpl(mockApi)
    }

    @Test
    fun `get posts remote success`() {
        // given
        whenever(mockApi.getPosts()).thenReturn(Single.just(remoteList))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockApi).getPosts()
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get posts remote fail`() {
        // given
        whenever(mockApi.getPosts()).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockApi).getPosts()
        test.assertError(throwable)
    }

    @Test
    fun `get post remote success`() {
        // given
        whenever(mockApi.getPost(postId)).thenReturn(Single.just(remoteItem))

        // when
        val test = dataSource.get(postId).test()

        // then
        verify(mockApi).getPost(postId)
        test.assertValue(remoteItem.mapToDomain())
    }

    @Test
    fun `get post remote fail`() {
        // given
        whenever(mockApi.getPost(postId)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get(postId).test()

        // then
        verify(mockApi).getPost(postId)
        test.assertError(throwable)
    }
}
