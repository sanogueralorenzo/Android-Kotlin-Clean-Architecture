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

    private lateinit var userRepository: UserRepositoryImpl

    private val mockUserApi = mock<UsersApi> {}
    private val mockCache = mock<Cache<List<UserEntity>>>()
    private val mapper = UserMapper(addressMapper = AddressMapper(GeoMapper()), companyMapper = CompanyMapper())

    private val key = "User List"

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl(mockUserApi, mockCache, mapper)
    }

    @Test
    fun `repository get remote list success`() {
        // given
        val entity = createUserEntity()
        val list = listOf(entity)
        _when(mockUserApi.getUsers()).thenReturn(Single.just(list))
        _when(mockCache.save(key, list)).thenReturn(Completable.complete())

        // when
        val test = userRepository.getRemote().test()

        // then
        verify(mockUserApi).getUsers()
        verify(mockCache).save(key, list)

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(listOf(mapper.mapToDomain(entity)))
    }

    @Test
    fun `repository get remote single success`() {
        // given
        val entity = createUserEntity()
        val list = listOf(entity)
        _when(mockUserApi.getUser(entity.id)).thenReturn(Single.just(entity))
        _when(mockCache.load(key, listOf())).thenReturn(Single.just(list))
        _when(mockCache.save(key, list)).thenReturn(Completable.complete())

        // when
        val test = userRepository.getRemote(entity.id).test()

        // then
        verify(mockUserApi).getUser(entity.id)

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(mapper.mapToDomain(entity))
    }

    @Test
    fun `repository get cache list success`() {
        // given
        val entity = createUserEntity()
        val list = listOf(entity)
        _when(mockCache.load(key, listOf())).thenReturn(Single.just(list))

        // when
        val test = userRepository.getCache(entity.id).test()

        // then
        verify(mockCache).load(key, listOf())

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(mapper.mapToDomain(entity))
    }

    @Test
    fun `repository get cache single success`() {
        // given
        val entity = createUserEntity()
        val list = listOf(entity)
        _when(mockCache.load(key, listOf())).thenReturn(Single.just(list))

        // when
        val test = userRepository.getCache(entity.id).test()

        // then
        verify(mockCache).load(key, listOf())

        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertComplete()
        test.assertValue(mapper.mapToDomain(entity))
    }

    @Test
    fun `repository get remote fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserApi.getUsers()).thenReturn(Single.error(throwable))

        // when
        val test = userRepository.getRemote().test()

        // then
        verify(mockUserApi).getUsers()

        test.assertError(throwable)
        test.assertValueCount(0)
        test.assertNotComplete()
        test.assertNoValues()
    }
}
