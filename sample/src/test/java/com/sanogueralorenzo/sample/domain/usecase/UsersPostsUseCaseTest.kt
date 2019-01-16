@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.domain.repository.PostRepository
import com.sanogueralorenzo.sample.domain.repository.UserRepository
import com.sanogueralorenzo.sample.post
import com.sanogueralorenzo.sample.user
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UsersPostsUseCaseTest {

    private lateinit var usersPostsUseCase: UsersPostsUseCase

    private val mockUserRepository: UserRepository = mock()
    private val mockPostRepository: PostRepository = mock()

    private val userList = listOf(user)
    private val postList = listOf(post)

    @Before
    fun setUp() {
        usersPostsUseCase = UsersPostsUseCase(mockUserRepository, mockPostRepository)
    }

    @Test
    fun `repository get success`() {
        // given
        whenever(mockUserRepository.get(false)).thenReturn(Single.just(userList))
        whenever(mockPostRepository.get(false)).thenReturn(Single.just(postList))

        // when
        val test = usersPostsUseCase.get(false).test()

        // then
        verify(mockUserRepository).get(false)
        verify(mockPostRepository).get(false)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(map(userList, postList))
    }

    @Test
    fun `repository get fail`() {
        // given
        val throwable = Throwable()
        whenever(mockUserRepository.get(false)).thenReturn(Single.error(throwable))
        whenever(mockPostRepository.get(false)).thenReturn(Single.error(throwable))

        // when
        val test = usersPostsUseCase.get(false).test()

        // then
        verify(mockUserRepository).get(false)
        verify(mockPostRepository).get(false)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}

class UserPostUseCaseTest {

    private lateinit var userPostUseCase: UserPostUseCase

    private val mockUserRepository: UserRepository = mock {}
    private val mockPostRepository: PostRepository = mock {}

    private val userId = user.id
    private val postId = post.id

    @Before
    fun setUp() {
        userPostUseCase = UserPostUseCase(mockUserRepository, mockPostRepository)
    }

    @Test
    fun `repository get success`() {
        // given
        whenever(mockUserRepository.get(userId, false)).thenReturn(Single.just(user))
        whenever(mockPostRepository.get(postId, false)).thenReturn(Single.just(post))

        // when
        val test = userPostUseCase.get(userId, postId, false).test()

        // then
        verify(mockUserRepository).get(userId, false)
        verify(mockPostRepository).get(postId, false)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(map(user, post))
    }

    @Test
    fun `repository get fail`() {
        // given
        val throwable = Throwable()
        whenever(mockUserRepository.get(userId, false)).thenReturn(Single.error(throwable))
        whenever(mockPostRepository.get(postId, false)).thenReturn(Single.error(throwable))

        // when
        val test = userPostUseCase.get(userId, postId, false).test()

        // then
        verify(mockUserRepository).get(userId, false)
        verify(mockPostRepository).get(postId, false)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
