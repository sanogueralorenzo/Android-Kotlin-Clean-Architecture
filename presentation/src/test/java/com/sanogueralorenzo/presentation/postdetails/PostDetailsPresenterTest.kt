@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.postdetails

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.presentation.createComment
import com.sanogueralorenzo.presentation.createPostItem
import com.sanogueralorenzo.presentation.model.CommentItemMapper
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class PostDetailsPresenterTest {

    private lateinit var presenter: PostDetailsPresenter

    private val mockUseCase = mock<CommentsUseCase> {}
    private val mockView = mock<PostDetailsView> {}
    private val mapper = CommentItemMapper()

    private val comment = createComment()
    private val postItem = createPostItem()

    //Rule to avoid having schedulers all around https://github.com/ReactiveX/RxAndroid/issues/238
    @Rule
    @JvmField
    val testRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        presenter = PostDetailsPresenter(mockUseCase, mapper)
        presenter.postId = postItem.postId
    }

    @Test
    fun `getting comments success`() {
        // given
        _when(mockUseCase.execute()).thenReturn(Flowable.just(listOf(comment)))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockUseCase).postId = postItem.postId
        verify(mockView).loading(true)
        verify(mockView).add(mapper.map(listOf(comment)))
        verify(mockView).loading(false)
    }

    @Test
    fun `getting comments fail`() {
        // given
        val throwable = Throwable()
        _when(mockUseCase.execute()).thenReturn(Flowable.error(throwable))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockView).loading(true)
        verify(mockView).error()
        verify(mockView).loading(false)
    }
}
