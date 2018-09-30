@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.posts.presentation.postdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanogueralorenzo.posts.combinedUserPost
import com.sanogueralorenzo.posts.comment
import com.sanogueralorenzo.posts.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.posts.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.posts.post
import com.sanogueralorenzo.posts.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.posts.presentation.model.mapToPresentation
import com.sanogueralorenzo.posts.user
import com.sanogueralorenzo.presentation.Resource
import com.sanogueralorenzo.presentation.ResourceState
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PostDetailsViewModelTest {

    private lateinit var viewModel: PostDetailsViewModel

    private val mockUserPostUseCase: UserPostUseCase = mock()
    private val mockCommentsUseCase: CommentsUseCase = mock()

    private val comments = listOf(comment)

    private val userId = user.id
    private val postId = post.id

    private val throwable = Throwable()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PostDetailsViewModel(mockUserPostUseCase, mockCommentsUseCase)
    }

    @Test
    fun `get post succeeds`() {
        // given
        whenever(mockUserPostUseCase.get(userId, postId, false))
            .thenReturn(Single.just(combinedUserPost))

        // when
        viewModel.getPost(UserIdPostId(userId, postId))

        // then
        verify(mockUserPostUseCase).get(userId, postId, false)
        assertEquals(combinedUserPost.mapToPresentation(), viewModel.post.value)
    }

    @Test
    fun `get comments succeeds`() {
        // given
        whenever(mockCommentsUseCase.get(postId, false)).thenReturn(Single.just(comments))

        // when
        viewModel.getComments(postId, false)

        // then
        verify(mockCommentsUseCase).get(postId, false)
        assertEquals(
            Resource(
                state = ResourceState.SUCCESS,
                data = comments.mapToPresentation(),
                message = null
            ), viewModel.comments.value
        )
    }

    @Test
    fun `get comments fails`() {
        // given
        whenever(mockCommentsUseCase.get(postId, true)).thenReturn(Single.error(throwable))

        // when
        viewModel.getComments(postId, true)

        // then
        verify(mockCommentsUseCase).get(postId, true)
        assertEquals(
            Resource(state = ResourceState.ERROR, data = null, message = throwable.message),
            viewModel.comments.value
        )
    }
}
