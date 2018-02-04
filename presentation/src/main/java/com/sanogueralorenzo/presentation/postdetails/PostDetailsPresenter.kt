package com.sanogueralorenzo.presentation.postdetails

import com.sanogueralorenzo.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.presentation.IView
import com.sanogueralorenzo.presentation.Presenter
import com.sanogueralorenzo.presentation.model.CommentItem
import com.sanogueralorenzo.presentation.model.CommentItemMapper
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface PostDetailsView : IView {
    fun showPost(item: PostItem)
    fun loading(show: Boolean)
    fun add(list: List<CommentItem>)
    fun error()
}

data class UserIdPostId(val userId: String, val postId: String)

class PostDetailsPresenter @Inject constructor(private val userPostUseCase: UserPostUseCase,
                                               private val commentsUseCase: CommentsUseCase,
                                               private val postItemMapper: PostItemMapper,
                                               private val commentItemMapper: CommentItemMapper) : Presenter<PostDetailsView>() {

    lateinit var userIdPostId: UserIdPostId

    override fun attachView(view: PostDetailsView) {
        super.attachView(view)
        userPostUseCase.userId = userIdPostId.userId
        userPostUseCase.postId = userIdPostId.postId
        commentsUseCase.postId = userIdPostId.postId
        getPost()
        getCommentsUseCase()
    }

    override fun detachView() {
        view?.loading(false)
        super.detachView()
    }

    private fun getPost() {
        addDisposable(userPostUseCase.execute()
                .subscribeOn(Schedulers.io())
                .map { postItemMapper.map(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showPost(it)
                }, { view?.error() }))
    }

    fun getCommentsUseCase() {
        view?.loading(true)
        addDisposable(commentsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .map { commentItemMapper.map(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.loading(false) }
                .subscribe({
                    view?.add(it)
                }, { view?.error() }))
    }
}
