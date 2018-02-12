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

class PostDetailsPresenter @Inject constructor(private val userPostUseCase: UserPostUseCase,
                                               private val commentsUseCase: CommentsUseCase,
                                               private val postItemMapper: PostItemMapper,
                                               private val commentItemMapper: CommentItemMapper) : Presenter<PostDetailsView>() {

    override fun attachView(view: PostDetailsView) {
        super.attachView(view)
        view.loading(true)
    }

    override fun detachView() {
        view?.loading(false)
        super.detachView()
    }

    fun getPost(userId: String, postId: String) {
        addDisposable(userPostUseCase.get(userId, postId, false)
                .subscribeOn(Schedulers.io())
                .map { postItemMapper.map(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showPost(it)
                }, { view?.error() }))
    }

    fun getComments(postId: String, refresh: Boolean = false) {
        addDisposable(commentsUseCase.get(postId, refresh)
                .subscribeOn(Schedulers.io())
                .map { commentItemMapper.map(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.loading(false) }
                .subscribe({ view?.add(it) }, { view?.error() }))
    }
}
