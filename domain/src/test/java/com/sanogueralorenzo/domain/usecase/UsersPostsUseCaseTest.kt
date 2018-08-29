@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.createPost
import com.sanogueralorenzo.domain.createUser
import com.sanogueralorenzo.domain.repository.PostRepository
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class UsersPostsUseCaseTest {

    private lateinit var usersPostsUseCase: UsersPostsUseCase

    private val mockUserRepository = mock<UserRepository>()
    private val mockPostRepository = mock<PostRepository>()
    private val mapper = UserPostMapper()

    private val userList = listOf(createUser())
    private val postList = listOf(createPost())

    @Before
    fun setUp() {
        usersPostsUseCase = UsersPostsUseCase(mockUserRepository, mockPostRepository, mapper)
    }

    @Test
    fun `repository get success`() {
        // given
        _when(mockUserRepository.get(false)).thenReturn(Single.just(userList))
        _when(mockPostRepository.get(false)).thenReturn(Single.just(postList))

        // when
        val test = usersPostsUseCase.get(false).test()

        // then
        verify(mockUserRepository).get(false)
        verify(mockPostRepository).get(false)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(userList, postList))
    }

    @Test
    fun `repository get fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserRepository.get(false)).thenReturn(Single.error(throwable))
        _when(mockPostRepository.get(false)).thenReturn(Single.error(throwable))

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

    private val mockUserRepository = mock<UserRepository> {}
    private val mockPostRepository = mock<PostRepository> {}
    private val mapper = UserPostMapper()

    private val userId = "1"
    private val postId = "1"

    private val user = createUser()
    private val post = createPost()

    @Before
    fun setUp() {
        userPostUseCase = UserPostUseCase(mockUserRepository, mockPostRepository, mapper)
    }

    @Test
    fun `repository get success`() {
        // given
        _when(mockUserRepository.get(userId, false)).thenReturn(Single.just(user))
        _when(mockPostRepository.get(postId, false)).thenReturn(Single.just(post))

        // when
        val test = userPostUseCase.get(userId, postId, false).test()

        // then
        verify(mockUserRepository).get(userId, false)
        verify(mockPostRepository).get(postId, false)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(user, post))
    }

    @Test
    fun `repository get fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserRepository.get(userId, false)).thenReturn(Single.error(throwable))
        _when(mockPostRepository.get(postId, false)).thenReturn(Single.error(throwable))

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
