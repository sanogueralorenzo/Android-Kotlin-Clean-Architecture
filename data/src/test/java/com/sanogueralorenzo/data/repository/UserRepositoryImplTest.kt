@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createUserEntity
import com.sanogueralorenzo.data.model.*
import com.sanogueralorenzo.data.remote.UsersApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl

    private val mockApi = mock<UsersApi> {}
    private val mockCache = mock<Cache<List<UserEntity>>>()
    private val mapper = UserMapper(addressMapper = AddressMapper(GeoMapper()), companyMapper = CompanyMapper())

    private val key = "User List"

    private val userId = "1"

    private val cacheItem = createUserEntity().copy(name = "cache")
    private val remoteItem = createUserEntity().copy(name = "remote")

    private val cacheList = listOf(cacheItem)
    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get users cache success`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(mapper.mapToDomain(cacheList))
    }

    @Test
    fun `get users cache fail fallback remote succeeds`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.error(throwable))
        _when(mockApi.getUsers()).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(false).test()

        // then
        verify(mockCache).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getUsers()
        test.assertValue(mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get user cache success`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.just(cacheList))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCache).load(key)
        test.assertValue(mapper.mapToDomain(cacheItem))
    }

    @Test
    fun `get user cache fail fallback remote succeeds`() {
        // given
        _when(mockCache.load(key)).thenReturn(Single.error(throwable), Single.just(emptyList()))
        _when(mockApi.getUser(userId)).thenReturn(Single.just(remoteItem))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(userId, false).test()

        // then
        verify(mockCache, times(2)).load(key)
        verify(mockCache).save(key, remoteList)
        verify(mockApi).getUser(userId)
        test.assertValue(mapper.mapToDomain(remoteItem))
    }


    @Test
    fun `get users remote success`() {
        // given
        _when(mockApi.getUsers()).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getUsers()
        verify(mockCache).save(key, remoteList)
        test.assertValue(mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get users remote fail`() {
        // given
        _when(mockApi.getUsers()).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(true).test()

        // then
        verify(mockApi).getUsers()
        test.assertError(throwable)
    }

    @Test
    fun `get user remote success`() {
        // given
        _when(mockApi.getUser(userId)).thenReturn(Single.just(remoteItem))
        _when(mockCache.load(key)).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.get(userId, true).test()

        // then
        verify(mockApi).getUser(userId)
        test.assertValue(mapper.mapToDomain(remoteItem))
    }

    @Test
    fun `get user remote fail`() {
        // given
        _when(mockApi.getUser(userId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.get(userId, true).test()

        // then
        verify(mockApi).getUser(userId)
        test.assertError(throwable)
    }
}
