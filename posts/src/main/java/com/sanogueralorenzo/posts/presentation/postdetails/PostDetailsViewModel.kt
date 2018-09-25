package com.sanogueralorenzo.posts.presentation.postdetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanogueralorenzo.posts.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.posts.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.posts.presentation.model.CommentItem
import com.sanogueralorenzo.posts.presentation.model.PostItem
import com.sanogueralorenzo.posts.presentation.model.mapToPresentation
import com.sanogueralorenzo.presentation.Data
import com.sanogueralorenzo.presentation.setError
import com.sanogueralorenzo.presentation.setLoading
import com.sanogueralorenzo.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class UserIdPostId(val userId: String, val postId: String)

class PostDetailsViewModel constructor(
    private val userPostUseCase: UserPostUseCase,
    private val commentsUseCase: CommentsUseCase
) : ViewModel() {

    val post = MutableLiveData<PostItem>()
    val comments = MutableLiveData<Data<List<CommentItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPost(ids: UserIdPostId) =
        compositeDisposable.add(userPostUseCase.get(ids.userId, ids.postId, false)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ post.postValue(it) }, { })
        )

    fun getComments(postId: String, refresh: Boolean = false) =
        compositeDisposable.add(commentsUseCase.get(postId, refresh)
            .doOnSubscribe { comments.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ comments.setSuccess(it) }, { comments.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
