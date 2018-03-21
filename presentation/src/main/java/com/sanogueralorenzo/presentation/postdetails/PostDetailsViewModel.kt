package com.sanogueralorenzo.presentation.postdetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanogueralorenzo.domain.usecase.CommentsUseCase
import com.sanogueralorenzo.domain.usecase.UserPostUseCase
import com.sanogueralorenzo.presentation.StateData
import com.sanogueralorenzo.presentation.model.CommentItem
import com.sanogueralorenzo.presentation.model.CommentItemMapper
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.observable.ObservableAutoConnect
import io.reactivex.observables.ConnectableObservable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class UserIdPostId(val userId: String, val postId: String)

class PostDetailsViewModel @Inject constructor(private val userPostUseCase: UserPostUseCase,
                                               private val commentsUseCase: CommentsUseCase,
                                               private val postItemMapper: PostItemMapper,
                                               private val commentItemMapper: CommentItemMapper) : ViewModel() {

    val post: MutableLiveData<PostItem> = MutableLiveData()
    val comments: MutableLiveData<List<CommentItem>> = MutableLiveData()
    val state: MutableLiveData<StateData> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var userIdPostId: UserIdPostId? = null
        set(value) {
            if (userIdPostId == null) {
                field = value
                getPost()
                getComments()
            }
        }

    fun getPost() =
            compositeDisposable.add(userPostUseCase.get(userIdPostId!!.userId, userIdPostId!!.postId, false)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { postItemMapper.mapToPresentation(it) }
                    .subscribe({ post.postValue(it) }, { }))


    fun getComments(refresh: Boolean = false) =
            compositeDisposable.add(commentsUseCase.get(userIdPostId!!.postId, refresh)
                    .doOnSubscribe { state.postValue(StateData.Loading) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { commentItemMapper.mapToPresentation(it) }
                    .subscribe({
                        state.postValue(StateData.Success)
                        comments.postValue(it)
                    }, { state.postValue(StateData.Error(it)) }))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
