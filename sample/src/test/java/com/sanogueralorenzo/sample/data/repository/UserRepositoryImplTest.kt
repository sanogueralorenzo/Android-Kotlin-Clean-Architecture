@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.data.datasource.UserCacheDataSource
import com.sanogueralorenzo.sample.data.datasource.UserRemoteDataSource
import com.sanogueralorenzo.sample.user
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl

    private val mockCacheDataSource: UserCacheDataSource = mock()
    private val mockRemoteDataSource: UserRemoteDataSource = mock()

    private val userId = user.id

    private val cacheItem = user.copy(name = "cache")
    private val remoteItem = user.copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val cacheThrowable = Throwable()
    private val remoteThrowable = Throwable()

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(mockCacheDataSource, mockRemoteDataSource)
    }

    @Test
    fun `get users cache success`() {
        // given
        whenever(mockCacheDataSource.get()).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCacheDataSource).get()
        test.assertValue(cacheList)
    }

    @Test
    fun `get users cache fail fallback remote succeeds`() {
        // given
        whenever(mockCacheDataSource.get()).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get()).thenReturn(Single.just(remoteList))
        whenever(mockCacheDataSource.set(remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCacheDataSource).get()
        verify(mockRemoteDataSource).get()
        verify(mockCacheDataSource).set(remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `get users cache fail fallback remote fails`() {
        // given
        whenever(mockCacheDataSource.get()).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get()).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCacheDataSource).get()
        verify(mockRemoteDataSource).get()
        test.assertError(remoteThrowable)
    }

    @Test
    fun `get users remote success`() {
        // given
        whenever(mockRemoteDataSource.get()).thenReturn(Single.just(remoteList))
        whenever(mockCacheDataSource.set(remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockRemoteDataSource).get()
        verify(mockCacheDataSource).set(remoteList)
        test.assertValue(remoteList)
    }

    @Test
    fun `get users remote fail`() {
        // given
        whenever(mockRemoteDataSource.get()).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockRemoteDataSource).get()
        test.assertError(remoteThrowable)
    }

    @Test
    fun `get user cache success`() {
        // given
        whenever(mockCacheDataSource.get(userId)).thenReturn(Single.just(cacheItem))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCacheDataSource).get(userId)
        test.assertValue(cacheItem)
    }

    @Test
    fun `get user cache fail fallback remote succeeds`() {
        // given
        whenever(mockCacheDataSource.get(userId)).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get(userId)).thenReturn(Single.just(remoteItem))
        whenever(mockCacheDataSource.set(remoteItem)).thenReturn(Single.just(remoteItem))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCacheDataSource).get(userId)
        verify(mockRemoteDataSource).get(userId)
        verify(mockCacheDataSource).set(remoteItem)
        test.assertValue(remoteItem)
    }

    @Test
    fun `get user cache fail fallback remote fails`() {
        // given
        whenever(mockCacheDataSource.get(userId)).thenReturn(Single.error(cacheThrowable))
        whenever(mockRemoteDataSource.get(userId)).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCacheDataSource).get(userId)
        verify(mockRemoteDataSource).get(userId)
        test.assertError(remoteThrowable)
    }

    @Test
    fun `get user remote success`() {
        // given
        whenever(mockRemoteDataSource.get(userId)).thenReturn(Single.just(remoteItem))
        whenever(mockCacheDataSource.set(remoteItem)).thenReturn(Single.just(remoteItem))

        // when
        val test = repository.get(userId, true).test()

        // then
        verify(mockRemoteDataSource).get(userId)
        verify(mockCacheDataSource).set(remoteItem)
        test.assertValue(remoteItem)
    }

    @Test
    fun `get user remote fail`() {
        // given
        whenever(mockRemoteDataSource.get()).thenReturn(Single.error(remoteThrowable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockRemoteDataSource).get()
        test.assertError(remoteThrowable)
    }
}
