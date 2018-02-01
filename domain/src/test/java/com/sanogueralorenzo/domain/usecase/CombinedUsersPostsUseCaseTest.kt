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
        _when(mockUserRepository.getCache()).thenReturn(Single.just(listOf(user)))
        _when(mockPostRepository.getCache()).thenReturn(Single.just(listOf(post)))
        _when(mockUserRepository.getRemote()).thenReturn(Single.just(listOf(user)))
        _when(mockPostRepository.getRemote()).thenReturn(Single.just(listOf(post)))

        // when
        val test = combinedUsersPostsUseCase.execute().test()

        // then
        verify(mockUserRepository).getCache()
        verify(mockPostRepository).getCache()
        verify(mockUserRepository).getRemote()
        verify(mockPostRepository).getRemote()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(2)
    }

    @Test
    fun `repository get cache success`() {
        // given
        _when(mockUserRepository.getCache()).thenReturn(Single.just(listOf(user)))
        _when(mockPostRepository.getCache()).thenReturn(Single.just(listOf(post)))

        // when
        val test = combinedUsersPostsUseCase.getCache().test()

        // then
        verify(mockUserRepository).getCache()
        verify(mockPostRepository).getCache()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(listOf(user), listOf(post)))
    }

    @Test
    fun `repository get cache fail`() {
        // given
        _when(mockUserRepository.getCache()).thenReturn(Single.just(listOf()))
        _when(mockPostRepository.getCache()).thenReturn(Single.just(listOf()))

        // when
        val test = combinedUsersPostsUseCase.getCache().test()

        // then
        verify(mockUserRepository).getCache()
        verify(mockPostRepository).getCache()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(listOf(), listOf()))
    }

    @Test
    fun `repository get remote success`() {
        // given
        _when(mockUserRepository.getRemote()).thenReturn(Single.just(listOf(user)))
        _when(mockPostRepository.getRemote()).thenReturn(Single.just(listOf(post)))

        // when
        val test = combinedUsersPostsUseCase.getRemote().test()

        // then
        verify(mockUserRepository).getRemote()
        verify(mockPostRepository).getRemote()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(listOf(user), listOf(post)))
    }

    @Test
    fun `repository get remote fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserRepository.getRemote()).thenReturn(Single.error(throwable))
        _when(mockPostRepository.getRemote()).thenReturn(Single.error(throwable))

        // when
        val test = combinedUsersPostsUseCase.getRemote().test()

        // then
        verify(mockUserRepository).getRemote()
        verify(mockPostRepository).getRemote()

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
