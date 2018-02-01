package com.sanogueralorenzo.presentation.postdetails

import com.sanogueralorenzo.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.presentation.IView
import com.sanogueralorenzo.presentation.Presenter
import com.sanogueralorenzo.presentation.model.CommentItem
import com.sanogueralorenzo.presentation.model.CommentItemMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface PostDetailsView : IView {
    fun loading(show: Boolean)
    fun add(list: List<CommentItem>)
    fun error()
}

class PostDetailsPresenter @Inject constructor(private val commentsUseCase: CommentsUseCase,
                                               private val mapper: CommentItemMapper) : Presenter<PostDetailsView>() {

    lateinit var postId: String

    override fun attachView(view: PostDetailsView) {
        super.attachView(view)
        commentsUseCase.postId = postId
        getCommentsUseCase()
    }

    override fun detachView() {
        view?.loading(false)
        super.detachView()
    }

    fun getCommentsUseCase() {
        view?.loading(true)
        addDisposable(commentsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .map { mapper.map(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { view?.loading(false) }
                .subscribe({
                    view?.add(it)
                }, { view?.error() }))
    }
}
