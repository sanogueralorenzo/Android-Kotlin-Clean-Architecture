@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.datasource.cache

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.cache.ReactiveCache
import com.sanogueralorenzo.sample.domain.model.User
import com.sanogueralorenzo.sample.user
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserCacheDataSourceImplTest {

    private lateinit var dataSource: UserCacheDataSourceImpl

    private val mockCache: ReactiveCache<List<User>> = mock()

    val key = "User List"

    private val userId = user.id

    private val cacheItem = user.copy(name = "cache")
    private val remoteItem = user.copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        dataSource = UserCacheDataSourceImpl(mockCache)
    }

    @Test
    fun `get users cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheList)
    }

    @Test
    fun `get users cache fail`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockCache).load(key)
        test.assertError(throwable)
    }

    @Test
    fun `get user cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = dataSource.get(userId).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheItem)
    }

    @Test
    fun `get user cache fail`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get(userId).test()

        // then
        verify(mockCache).load(key)
        test.assertError(throwable)
    }

    @Test
    fun `set users cache success`() {
        // given
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = dataSource.set(remoteList).test()

        // then
        verify(mockCache).save(key, remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `set users cache fail`() {
        // given
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.set(remoteList).test()

        // then
        verify(mockCache).save(key, remoteList)
        test.assertError(throwable)
    }

    @Test
    fun `set user cache success`() {
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
    fun `set user cache fail`() {
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
