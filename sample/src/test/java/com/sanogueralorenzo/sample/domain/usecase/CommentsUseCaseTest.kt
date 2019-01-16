@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.sample.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.sample.comment
import com.sanogueralorenzo.sample.domain.repository.CommentRepository
import com.sanogueralorenzo.sample.post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class CommentsUseCaseTest {

    private lateinit var usecase: CommentsUseCase

    private val mockRepository: CommentRepository = mock()

    private val postId = post.id
    private val commentList = listOf(comment)

    @Before
    fun setUp() {
        usecase = CommentsUseCase(mockRepository)
    }

    @Test
    fun `repository get success`() {
        // given
        whenever(mockRepository.get(postId, false)).thenReturn(Single.just(commentList))

        // when
        val test = usecase.get(postId, false).test()

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
        whenever(mockRepository.get(postId, false)).thenReturn(Single.error(throwable))

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
