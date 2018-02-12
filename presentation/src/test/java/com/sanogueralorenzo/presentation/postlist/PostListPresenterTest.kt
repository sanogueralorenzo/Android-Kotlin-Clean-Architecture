@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.postlist

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.sanogueralorenzo.domain.usecase.CombinedUserPost
import com.sanogueralorenzo.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.presentation.createPost
import com.sanogueralorenzo.presentation.createUser
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class PostListPresenterTest {

    private lateinit var presenter: PostListPresenter

    private val mockUseCase = mock<UsersPostsUseCase> {}
    private val mockView = mock<PostListView> {}
    private val mapper = PostItemMapper()

    //Rule to avoid having schedulers all around https://github.com/ReactiveX/RxAndroid/issues/238
    @Rule
    @JvmField
    val testRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    private val user = createUser()
    private val post = createPost()
    private val combinedUserPost = CombinedUserPost(user, post)

    private val throwable = Throwable()


    @Before
    fun setUp() {
        presenter = PostListPresenter(mockUseCase, mapper)
    }

    @Test
    fun `attaching view shows loading`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.just(listOf(combinedUserPost)))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockView).loading(true)
    }

    @Test
    fun `detaching view hides loading`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.just(listOf(combinedUserPost)))

        // when
        presenter.attachView(mockView)
        reset(mockView)
        presenter.detachView()

        // then
        verify(mockView).loading(false)
    }

    @Test
    fun `getting post user success`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.just(listOf(combinedUserPost)))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockView).loading(true)
        verify(mockView).add(mapper.map(listOf(combinedUserPost)))
        verify(mockView).loading(false)
    }

    @Test
    fun `getting post user fail`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.error(throwable))

        // when
        presenter.attachView(mockView)

        // then
        verify(mockView).error()
        verify(mockView).loading(false)
    }
}
