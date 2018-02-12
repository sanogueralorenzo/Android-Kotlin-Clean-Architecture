@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.postdetails

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.sanogueralorenzo.domain.usecase.CombinedUserPost
import com.sanogueralorenzo.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.presentation.createComment
import com.sanogueralorenzo.presentation.createPost
import com.sanogueralorenzo.presentation.createUser
import com.sanogueralorenzo.presentation.model.CommentItemMapper
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class PostDetailsPresenterTest {

    private lateinit var presenter: PostDetailsPresenter

    private val mockUserPostUseCase = mock<UserPostUseCase> {}
    private val mockCommentsUseCase = mock<CommentsUseCase> {}
    private val mockView = mock<PostDetailsView> {}
    private val postItemMapper = PostItemMapper()
    private val commentItemMapper = CommentItemMapper()

    private val combinedUserPost = CombinedUserPost(createUser(), createPost())
    private val comment = createComment()

    private val userId = "1"
    private val postId = "1"

    //Rule to avoid having schedulers all around https://github.com/ReactiveX/RxAndroid/issues/238
    @Rule
    @JvmField
    val testRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        presenter = PostDetailsPresenter(mockUserPostUseCase, mockCommentsUseCase, postItemMapper, commentItemMapper)
        presenter.attachView(mockView)
    }

    @Test
    fun `attaching view shows loading`() {
        // given

        // when

        // then
        verify(mockView).loading(true)
    }

    @Test
    fun `detaching view hides loading`() {
        // given

        // when
        reset(mockView)
        presenter.detachView()

        // then
        verify(mockView).loading(false)
    }

    @Test
    fun `getting post success`() {
        // given
        _when(mockUserPostUseCase.get(userId, postId, false)).thenReturn(Single.just(combinedUserPost))

        // when
        presenter.getPost(userId, postId)

        // then
        verify(mockView).showPost(postItemMapper.map(combinedUserPost))
    }

    @Test
    fun `getting comments success`() {
        // given
        _when(mockCommentsUseCase.get(postId, false)).thenReturn(Single.just(listOf(comment)))

        // when
        presenter.getComments(postId)

        // then
        verify(mockView).add(commentItemMapper.map(listOf(comment)))
        verify(mockView).loading(false)
    }

    @Test
    fun `getting comments fail`() {
        // given
        val throwable = Throwable()
        _when(mockCommentsUseCase.get(postId, false)).thenReturn(Single.error(throwable))

        // when
        presenter.getComments(postId)

        // then
        verify(mockView).error()
        verify(mockView).loading(false)
    }
}
