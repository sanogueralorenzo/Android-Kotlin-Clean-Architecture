@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.createComment
import com.sanogueralorenzo.domain.repository.CommentRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class CommentsUseCaseTest {

    private lateinit var usecase: CommentsUseCase

    private val mockRepository = mock<CommentRepository> {}

    private val postId = "1"
    private val comment = createComment()

    @Before
    fun setUp() {
        usecase = CommentsUseCase(mockRepository)
        usecase.postId = postId
    }

    @Test
    fun `repository execute success`() {
        // given
        _when(mockRepository.getCache(postId)).thenReturn(Single.just(listOf(comment)))
        _when(mockRepository.getRemote(postId)).thenReturn(Single.just(listOf(comment)))

        // when
        val test = usecase.execute().test()

        // then
        verify(mockRepository).getCache(postId)
        verify(mockRepository).getRemote(postId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(2)
    }

    @Test
    fun `repository get cache success`() {
        // given
        _when(mockRepository.getCache(postId)).thenReturn(Single.just(listOf(comment)))

        // when
        val test = usecase.getCache().test()

        // then
        verify(mockRepository).getCache(postId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(listOf(comment))
    }

    @Test
    fun `repository get cache fail`() {
        // given
        _when(mockRepository.getCache(postId)).thenReturn(Single.just(listOf()))

        // when
        val test = usecase.getCache().test()

        // then
        verify(mockRepository).getCache(postId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(listOf())
    }

    @Test
    fun `repository get remote success`() {
        // given
        _when(mockRepository.getRemote(postId)).thenReturn(Single.just(listOf(comment)))

        // when
        val test = usecase.getRemote().test()

        // then
        verify(mockRepository).getRemote(postId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(listOf(comment))
    }

    @Test
    fun `repository get remote fail`() {
        // given
        val throwable = Throwable()
        _when(mockRepository.getRemote(postId)).thenReturn(Single.error(throwable))

        // when
        val test = usecase.getRemote().test()

        // then
        verify(mockRepository).getRemote(postId)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
