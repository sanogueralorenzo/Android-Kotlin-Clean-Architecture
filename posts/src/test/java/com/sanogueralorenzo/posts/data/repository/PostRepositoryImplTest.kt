@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.posts.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.data.model.PostEntity
import com.sanogueralorenzo.posts.data.model.mapToDomain
import com.sanogueralorenzo.posts.data.remote.PostsApi
import com.sanogueralorenzo.posts.post
import com.sanogueralorenzo.posts.postEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostRepositoryImplTest {

    private lateinit var repository: PostRepositoryImpl

    private val mockApi: PostsApi = mock()
    private val mockCache: Cache<List<PostEntity>> = mock()

    private val key = "Post List"

    private val postId = post.id

    private val cacheItem = postEntity.copy(title = "cache")
    private val remoteItem = postEntity.copy(title = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = PostRepositoryImpl(mockApi, mockCache)
    }

    @Test
    fun `get posts cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheList.mapToDomain())
    }

    @Test
    fun `get posts cache fail fallback remote succeeds`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))
        whenever(mockApi.getPosts()).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getPosts()
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get post cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheItem.mapToDomain())
    }

    @Test
    fun `get post cache fail fallback remote succeeds`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable), Single.just(emptyList()))
        whenever(mockApi.getPost(postId)).thenReturn(Single.just(remoteItem))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache, times(2)).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getPost(postId)
        test.assertValue(remoteItem.mapToDomain())
    }

    @Test
    fun `get posts remote success`() {
        // given
        whenever(mockApi.getPosts()).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getPosts()
        verify(mockCache).save(key, remoteList)
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get posts remote fail`() {
        // given
        whenever(mockApi.getPosts()).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getPosts()
        test.assertError(throwable)
    }

    @Test
    fun `get post remote success`() {
        // given
        whenever(mockApi.getPost(postId)).thenReturn(Single.just(remoteItem))
        whenever(mockCache.load(key)).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getPost(postId)
        test.assertValue(remoteItem.mapToDomain())
    }

    @Test
    fun `get post remote fail`() {
        // given
        whenever(mockApi.getPost(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getPost(postId)
        test.assertError(throwable)
    }
}
