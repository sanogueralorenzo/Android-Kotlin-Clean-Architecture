@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.createPost
import com.sanogueralorenzo.domain.createUser
import com.sanogueralorenzo.domain.repository.PostRepository
import com.sanogueralorenzo.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class CombinedUsersPostsUseCaseTest {

    private lateinit var combinedUsersPostsUseCase: CombinedUsersPostsUseCase

    private val mockUserRepository = mock<UserRepository> {}
    private val mockPostRepository = mock<PostRepository> {}
    private val mapper = CombinedUserPostMapper()

    private val user = createUser()
    private val post = createPost()

    @Before
    fun setUp() {
        combinedUsersPostsUseCase = CombinedUsersPostsUseCase(mockUserRepository, mockPostRepository, mapper)
    }

    @Test
    fun `repository execute success`() {
        // given
        _when(mockUserRepository.getUsers()).thenReturn(Flowable.just(listOf(user)))
        _when(mockPostRepository.getPosts()).thenReturn(Flowable.just(listOf(post)))

        // when
        val test = combinedUsersPostsUseCase.execute().test()

        // then
        verify(mockUserRepository).getUsers()
        verify(mockPostRepository).getPosts()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
    }

    @Test
    fun `repository execute fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserRepository.getUsers()).thenReturn(Flowable.error(throwable))
        _when(mockPostRepository.getPosts()).thenReturn(Flowable.error(throwable))

        // when
        val test = combinedUsersPostsUseCase.execute().test()

        // then
        verify(mockUserRepository).getUsers()
        verify(mockPostRepository).getPosts()

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
