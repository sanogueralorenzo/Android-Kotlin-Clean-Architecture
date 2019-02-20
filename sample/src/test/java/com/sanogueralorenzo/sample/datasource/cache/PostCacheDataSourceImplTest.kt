@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.datasource.cache

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.cache.ReactiveCache
import com.sanogueralorenzo.sample.domain.model.Post
import com.sanogueralorenzo.sample.post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostCacheDataSourceImplTest {

    private lateinit var dataSource: PostCacheDataSourceImpl

    private val mockCache: ReactiveCache<List<Post>> = mock()

    val key = "Post List"

    private val postId = post.id

    private val cacheItem = post.copy(title = "cache")
    private val remoteItem = post.copy(title = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        dataSource = PostCacheDataSourceImpl(mockCache)
    }

    @Test
    fun `get posts cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheList)
    }

    @Test
    fun `get posts cache fail`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockCache).load(key)
        test.assertError(throwable)
    }

    @Test
    fun `get post cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = dataSource.get(postId).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheItem)
    }

    @Test
    fun `get post cache fail`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get(postId).test()

        // then
        verify(mockCache).load(key)
        test.assertError(throwable)
    }

    @Test
    fun `set posts cache success`() {
        // given
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = dataSource.set(remoteList).test()

        // then
        verify(mockCache).save(key, remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `set posts cache fail`() {
        // given
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.set(remoteList).test()

        // then
        verify(mockCache).save(key, remoteList)
        test.assertError(throwable)
    }

    @Test
    fun `set post cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(emptyList()))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = dataSource.set(remoteItem).test()

        // then
        verify(mockCache).save(key, remoteList)
        test.assertValue(remoteItem)
    }

    @Test
    fun `set post cache fail`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(emptyList()))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.set(remoteItem).test()

        // then
        verify(mockCache).save(key, remoteList)
        test.assertError(throwable)
    }
}
