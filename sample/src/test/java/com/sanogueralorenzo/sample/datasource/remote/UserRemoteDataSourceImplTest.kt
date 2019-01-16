@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.datasource.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.datasource.model.mapToDomain
import com.sanogueralorenzo.sample.user
import com.sanogueralorenzo.sample.userEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserRemoteDataSourceImplTest {

    private lateinit var dataSource: UserRemoteDataSourceImpl

    private val mockApi: UsersApi = mock()

    private val userId = user.id

    private val remoteItem = userEntity.copy(name = "remote")

    private val remoteList = listOf(remoteItem)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        dataSource = UserRemoteDataSourceImpl(mockApi)
    }

    @Test
    fun `get users remote success`() {
        // given
        whenever(mockApi.getUsers()).thenReturn(Single.just(remoteList))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockApi).getUsers()
        test.assertValue(remoteList.mapToDomain())
    }

    @Test
    fun `get users remote fail`() {
        // given
        whenever(mockApi.getUsers()).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get().test()

        // then
        verify(mockApi).getUsers()
        test.assertError(throwable)
    }

    @Test
    fun `get user remote success`() {
        // given
        whenever(mockApi.getUser(userId)).thenReturn(Single.just(remoteItem))

        // when
        val test = dataSource.get(userId).test()

        // then
        verify(mockApi).getUser(userId)
        test.assertValue(remoteItem.mapToDomain())
    }

    @Test
    fun `get user remote fail`() {
        // given
        whenever(mockApi.getUser(userId)).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.get(userId).test()

        // then
        verify(mockApi).getUser(userId)
        test.assertError(throwable)
    }
}
