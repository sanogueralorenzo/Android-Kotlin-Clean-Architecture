@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.postlist

import com.nhaarman.mockito_kotlin.mock
import com.sanogueralorenzo.domain.usecase.CombinedUserPost
import com.sanogueralorenzo.domain.usecase.CombinedUsersPostsUseCase
import com.sanogueralorenzo.presentation.RxSchedulersOverrideRule
import com.sanogueralorenzo.presentation.createPost
import com.sanogueralorenzo.presentation.createUser
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as _when

class PostListPresenterTest {

    private lateinit var presenter: PostListPresenter

    private val mockUseCase = mock<CombinedUsersPostsUseCase> {}
    private val mockView = mock<PostListView> {}
    private val mapper = PostItemMapper()

    //Rule to avoid having schedulers all around https://github.com/ReactiveX/RxAndroid/issues/238
    @Rule
    @JvmField
    val testRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        presenter = PostListPresenter(mockUseCase, mapper)
    }

    @Test
    fun `getting post user success`() {
        // given
        val user = createUser()
        val post = createPost()
        val combinedUserPost = CombinedUserPost(user, post)
        _when(mockUseCase.execute()).thenReturn(Flowable.just(listOf(combinedUserPost)))

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
