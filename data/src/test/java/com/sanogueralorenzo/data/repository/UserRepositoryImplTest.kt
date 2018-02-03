@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.data.cache.Cache
import com.sanogueralorenzo.data.createUserEntity
import com.sanogueralorenzo.data.model.*
import com.sanogueralorenzo.data.remote.UsersApi
import io.reactivex.Completable
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

    private val cacheEntity = createUserEntity().copy(id = "cache")
    private val remoteEntity = createUserEntity().copy(id = "remote")

    private val cacheEntityList = listOf(cacheEntity)
    private val remoteEntityList = listOf(remoteEntity)

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(mockApi, mockCache, mapper)
    }

    @Test
    fun `get users success`() {
        // given
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheEntityList))
        _when(mockApi.getUsers()).thenReturn(Single.just(remoteEntityList))
        _when(mockCache.save(key, remoteEntityList)).thenReturn(Single.just()))

        // when
        val test = repository.getUsers().test()

        // then
        verify(mockApi).getUsers()
        verify(mockCache).load(key, emptyList())
        verify(mockCache).save(key, remoteEntityList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValues(mapper.mapToDomain(cacheEntityList), mapper.mapToDomain(remoteEntityList))
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
        verify(mockCache, never()).save(key, remoteEntityList)

        test.assertError(throwable)
        test.assertValueCount(1)
        test.assertNotComplete()
        test.assertValue(emptyList())
    }

    @Test
    fun `get user success`() {
        // given
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheEntityList))
        _when(mockApi.getUser(userId)).thenReturn(Single.just(remoteEntity))
        _when(mockCache.save(key, remoteEntityList)).thenReturn(Completable.complete())

        // when
        val test = repository.getUser(userId).test()

        // then
        verify(mockApi).getUser(userId)
        verify(mockCache).load(key, emptyList())
        verify(mockCache).save(key, remoteEntityList)

        test.assertNoErrors()
        test.assertValueCount(2)
        test.assertComplete()
        test.assertValues(mapper.mapToDomain(cacheEntity), mapper.mapToDomain(remoteEntity))
    }

    @Test
    fun `get user fail`() {
        // given
        val throwable = Throwable()
        _when(mockCache.load(key, emptyList())).thenReturn(Single.just(cacheEntityList))
        _when(mockApi.getUser(userId)).thenReturn(Single.error(throwable))

        // when
        val test = repository.getUser(userId).test()

        // then
        verify(mockApi).getUser(userId)
        verify(mockCache).load(key, emptyList())
        verify(mockCache, never()).save(key, remoteEntityList)

        test.assertError(throwable)
        test.assertValueCount(1)
        test.assertNotComplete()
        test.assertValue(mapper.mapToDomain(cacheEntity))
    }
}
