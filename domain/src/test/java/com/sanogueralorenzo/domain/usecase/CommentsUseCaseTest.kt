@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.createComment
import com.sanogueralorenzo.domain.repository.CommentRepository
import io.reactivex.Flowable
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
        usecase.postId = postId
    }

    @Test
    fun `repository execute success`() {
        // given
        _when(mockRepository.getComments(postId)).thenReturn(Flowable.just(commentList))

        // when
        val test = usecase.execute().test()

        // then
        verify(mockRepository).getComments(postId)

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(commentList)
    }

    @Test
    fun `repository execute fail`() {
        // given
        val throwable = Throwable()
        _when(mockRepository.getComments(postId)).thenReturn(Flowable.error(throwable))

        // when
        val test = usecase.execute().test()

        // then
        verify(mockRepository).getComments(postId)

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}
