package com.sanogueralorenzo.sample.presentation.postdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanogueralorenzo.sample.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.sample.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.sample.presentation.model.CommentItem
import com.sanogueralorenzo.sample.presentation.model.PostItem
import com.sanogueralorenzo.sample.presentation.model.mapToPresentation
import com.sanogueralorenzo.presentation.Resource
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
    val comments = MutableLiveData<Resource<List<CommentItem>>>()
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
