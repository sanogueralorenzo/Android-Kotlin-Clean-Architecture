@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.createPost
import com.sanogueralorenzo.domain.createUser
import com.sanogueralorenzo.domain.repository.PostRepository
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class UsersPostsUseCaseTest {

    private lateinit var usersPostsUseCase: UsersPostsUseCase

    private val mockUserRepository = mock<UserRepository> {}
    private val mockPostRepository = mock<PostRepository> {}
    private val mapper = UserPostMapper()

    private val userList = listOf(createUser())
    private val postList = listOf(createPost())

    @Before
    fun setUp() {
        usersPostsUseCase = UsersPostsUseCase(mockUserRepository, mockPostRepository, mapper)
    }

    @Test
    fun `repository execute success`() {
        // given
        _when(mockUserRepository.getUsers()).thenReturn(Flowable.just(userList))
        _when(mockPostRepository.getPosts()).thenReturn(Flowable.just(postList))

        // when
        val test = usersPostsUseCase.execute().test()

        // then
        verify(mockUserRepository).getUsers()
        verify(mockPostRepository).getPosts()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(userList, postList))
    }

    @Test
    fun `repository execute fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserRepository.getUsers()).thenReturn(Flowable.error(throwable))
        _when(mockPostRepository.getPosts()).thenReturn(Flowable.error(throwable))

        // when
        val test = usersPostsUseCase.execute().test()

        // then
        verify(mockUserRepository).getUsers()
        verify(mockPostRepository).getPosts()

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
        userPostUseCase.userId = userId
        userPostUseCase.postId = postId
    }

    @Test
    fun `repository execute success`() {
        // given
        _when(mockUserRepository.getUser(userId)).thenReturn(Flowable.just(user))
        _when(mockPostRepository.getPost(postId)).thenReturn(Flowable.just(post))

        // when
        val test = userPostUseCase.execute().test()

        // then
        verify(mockUserRepository).getUser(userId)
        verify(mockPostRepository).getPost(postId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(user, post))
    }

    @Test
    fun `repository execute fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserRepository.getUser(userId)).thenReturn(Flowable.error(throwable))
        _when(mockPostRepository.getPost(userId)).thenReturn(Flowable.error(throwable))

        // when
        val test = userPostUseCase.execute().test()

        // then
        verify(mockUserRepository).getUser(userId)
        verify(mockPostRepository).getPost(postId)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
