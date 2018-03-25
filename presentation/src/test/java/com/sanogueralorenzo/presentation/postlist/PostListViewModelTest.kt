@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.postlist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.usecase.CombinedUserPost
import com.sanogueralorenzo.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.presentation.*
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when` as _when

class PostListViewModelTest {

    private lateinit var viewModel: PostListViewModel

    private val mockUseCase = mock<UsersPostsUseCase> {}
    private val mapper = PostItemMapper()

    private val user = createUser()
    private val post = createPost()

    private val combinedUserPosts = listOf(CombinedUserPost(user, post))
    private val throwable = Throwable()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `get post item list succeeds`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.just(combinedUserPosts))

        // when
        viewModel = PostListViewModel(mockUseCase, mapper)

        // then
        assertEquals(Data(dataState = DataState.SUCCESS, data = mapper.mapToPresentation(combinedUserPosts), message = null), viewModel.posts.value)
    }

    @Test
    fun `get post item list fails`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.error(throwable))

        // when
        viewModel = PostListViewModel(mockUseCase, mapper)

        // then
        assertEquals(Data(dataState = DataState.ERROR, data = null, message = throwable.message), viewModel.posts.value)
    }
}
