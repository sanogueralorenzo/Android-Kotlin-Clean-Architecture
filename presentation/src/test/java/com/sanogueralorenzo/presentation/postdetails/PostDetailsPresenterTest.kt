@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.postdetails

import com.nhaarman.mockito_kotlin.mock
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
        presenter.userIdPostId = UserIdPostId(userId, postId)
    }

    @Test
    fun `getting post success`() {
        // given
        _when(mockUserPostUseCase.execute()).thenReturn(Flowable.just(combinedUserPost))
        _when(mockCommentsUseCase.execute()).thenReturn(Flowable.empty())

        // when
        presenter.attachView(mockView)

        // then
        verify(mockUserPostUseCase).userId = presenter.userIdPostId.userId
        verify(mockUserPostUseCase).postId = presenter.userIdPostId.postId
        verify(mockView).showPost(postItemMapper.map(combinedUserPost))
    }

    @Test
    fun `getting comments success`() {
        // given
        _when(mockUserPostUseCase.execute()).thenReturn(Flowable.empty())
        _when(mockCommentsUseCase.execute()).thenReturn(Flowable.just(listOf(comment)))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockCommentsUseCase).postId = presenter.userIdPostId.postId
        verify(mockView).loading(true)
        verify(mockView).add(commentItemMapper.map(listOf(comment)))
        verify(mockView).loading(false)
    }

    @Test
    fun `getting comments fail`() {
        // given
        val throwable = Throwable()
        _when(mockUserPostUseCase.execute()).thenReturn(Flowable.empty())
        _when(mockCommentsUseCase.execute()).thenReturn(Flowable.error(throwable))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockView).loading(true)
        verify(mockView).error()
        verify(mockView).loading(false)
    }
}
