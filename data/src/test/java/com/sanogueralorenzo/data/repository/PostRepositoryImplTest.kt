@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createPostEntity
import com.sanogueralorenzo.data.model.PostEntity
import com.sanogueralorenzo.data.model.PostMapper
import com.sanogueralorenzo.data.remote.PostsApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as _when

class PostRepositoryImplTest {

    private lateinit var repository: PostRepositoryImpl

    private val mockApi = mock<PostsApi>()
    private val mockCache = mock<Cache<List<PostEntity>>>()
    private val mapper = PostMapper()

    private val key = "Post List"

    private val postId = "1"

    private val cacheItem = createPostEntity().copy(title = "cache")
    private val remoteItem = createPostEntity().copy(title = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = PostRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get posts cache success`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(mapper.mapToDomain(cacheList))
    }

    @Test
    fun `get posts cache fail fallback remote succeeds`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.error(throwable))
        _when(mockApi.getPosts()).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getPosts()
        test.assertValue(mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get post cache success`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(mapper.mapToDomain(cacheItem))
    }

    @Test
    fun `get post cache fail fallback remote succeeds`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.error(throwable), Single.just(emptyList()))
        _when(mockApi.getPost(postId)).thenReturn(Single.just(remoteItem))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, false).test()

        // then
        verify(mockCache, times(2)).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getPost(postId)
        test.assertValue(mapper.mapToDomain(remoteItem))
    }

    @Test
    fun `get posts remote success`() {
        // given
        _when(mockApi.getPosts()).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getPosts()
        verify(mockCache).save(key, remoteList)
        test.assertValue(mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get posts remote fail`() {
        // given
        _when(mockApi.getPosts()).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getPosts()
        test.assertError(throwable)
    }

    @Test
    fun `get post remote success`() {
        // given
        _when(mockApi.getPost(postId)).thenReturn(Single.just(remoteItem))
        _when(mockCache.load(key)).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getPost(postId)
        test.assertValue(mapper.mapToDomain(remoteItem))
    }

    @Test
    fun `get post remote fail`() {
        // given
        _when(mockApi.getPost(postId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(postId, true).test()

        // then
        verify(mockApi).getPost(postId)
        test.assertError(throwable)
    }
}
