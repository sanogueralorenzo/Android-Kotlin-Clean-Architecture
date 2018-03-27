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
    private val commentList = listOf(createComment())

    @Before
    fun setUp() {
        usecase = CommentsUseCase(mockRepository)
    }

    @Test
    fun `repository get success`() {
        // given
        _when(mockRepository.get(postId, false)).thenReturn(Single.just(commentList))

        // when
        val test = usecase.get(postId, true).test()

        // then
        verify(mockRepository).get(postId, false)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(commentList)
    }

    @Test
    fun `repository get fail`() {
        // given
        val throwable = Throwable()
        _when(mockRepository.get(postId, false)).thenReturn(Single.error(throwable))

        // when
        val test = usecase.get(postId, false).test()

        // then
        verify(mockRepository).get(postId, false)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
