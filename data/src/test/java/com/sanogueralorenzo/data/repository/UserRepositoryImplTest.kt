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

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get users success`() {
        // given
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheList))
        _when(mockApi.getUsers()).thenReturn(Single.just(remoteList))
        _when(mockCache.save(key, remoteList)).thenReturn(Single.just(remoteList))

        // when
        val test = repository.getUsers().test()

        // then
        verify(mockCache).load(key, emptyList())
        verify(mockApi).getUsers()
        verify(mockCache).save(key, remoteList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValues(mapper.mapToDomain(cacheList), mapper.mapToDomain(remoteList))
    }

    @Test
    fun `get users fail`() {
        // given
        val throwable = Throwable()
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(emptyList()))
        _when(mockApi.getUsers()).thenReturn(Single.error(throwable))

        // when
        val test = repository.getUsers().test()

        // then
        verify(mockApi).getUsers()
        verify(mockCache).load(key, emptyList())
        verify(mockCache, never()).save(anyString(), anyList())

        test.assertError(throwable)
        test.assertValueCount(1)
        test.assertNotComplete()
        test.assertValue(emptyList())
    }

    @Test
    fun `get user success`() {
        // given
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheList))
        _when(mockApi.getUser(userId)).thenReturn(Single.just(remoteItem))
        _when(mockCache.save(anyString(), anyList())).thenReturn(Single.just(cacheList))

        // when
        val test = repository.getUser(userId).test()

        // then
        verify(mockApi).getUser(userId)
        verify(mockCache, times(2)).load(key, emptyList())
        verify(mockCache).save(key, listOf(remoteItem))

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValues(mapper.mapToDomain(cacheItem), mapper.mapToDomain(remoteItem))
    }

    @Test
    fun `get user fail`() {
        // given
        val throwable = Throwable()
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheList))
        _when(mockApi.getUser(userId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.getUser(userId).test()

        // then
        verify(mockApi).getUser(userId)
        verify(mockCache).load(key, emptyList())
        verify(mockCache, never()).save(anyString(), anyList())

        test.assertError(throwable)
        test.assertValueCount(1)
        test.assertNotComplete()
        test.assertValue(mapper.mapToDomain(cacheItem))
    }
}
