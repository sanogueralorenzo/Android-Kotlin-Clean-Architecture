@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.posts.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.cache.Cache
import com.sanogueralorenzo.posts.data.model.UserEntity
import com.sanogueralorenzo.posts.data.model.mapToDomain
import com.sanogueralorenzo.posts.data.remote.UsersApi
import com.sanogueralorenzo.posts.user
import com.sanogueralorenzo.posts.userEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl

    private val mockApi: UsersApi = mock()
    private val mockCache: Cache<List<UserEntity>> = mock()

    private val key = "User List"

    private val userId = user.id

    private val cacheItem = userEntity.copy(name = "cache")
    private val remoteItem = userEntity.copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(mockApi, mockCache)
    }

    @Test
    fun `get users cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheList.mapToDomain())
    }

    @Test
    fun `get users cache fail fallback remote succeeds`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable))
        whenever(mockApi.getUsers()).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getUsers()
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get user cache success`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(cacheItem.mapToDomain())
    }

    @Test
    fun `get user cache fail fallback remote succeeds`() {
        // given
        whenever(mockCache.load(key)).thenReturn(Single.error(throwable), Single.just(emptyList()))
        whenever(mockApi.getUser(userId)).thenReturn(Single.just(remoteItem))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCache, times(2)).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getUser(userId)
        test.assertValue(remoteItem.mapToDomain())
    }

    @Test
    fun `get users remote success`() {
        // given
        whenever(mockApi.getUsers()).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getUsers()
        verify(mockCache).save(key, remoteList)
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get users remote fail`() {
        // given
        whenever(mockApi.getUsers()).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getUsers()
        test.assertError(throwable)
    }

    @Test
    fun `get user remote success`() {
        // given
        whenever(mockApi.getUser(userId)).thenReturn(Single.just(remoteItem))
        whenever(mockCache.load(key)).thenReturn(Single.just(remoteList))
        whenever(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(userId, true).test()

        // then
        verify(mockApi).getUser(userId)
        test.assertValue(remoteItem.mapToDomain())
    }

    @Test
    fun `get user remote fail`() {
        // given
        whenever(mockApi.getUser(userId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(userId, true).test()

        // then
        verify(mockApi).getUser(userId)
        test.assertError(throwable)
    }
}
