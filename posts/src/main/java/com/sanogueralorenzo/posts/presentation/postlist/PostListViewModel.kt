package com.sanogueralorenzo.posts.presentation.postlist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanogueralorenzo.posts.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.posts.presentation.model.PostItem
import com.sanogueralorenzo.posts.presentation.model.mapToPresentation
import com.sanogueralorenzo.presentation.Data
import com.sanogueralorenzo.presentation.setError
import com.sanogueralorenzo.presentation.setLoading
import com.sanogueralorenzo.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostListViewModel constructor(private val useCase: UsersPostsUseCase) : ViewModel() {

    val posts = MutableLiveData<Data<List<PostItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun get(refresh: Boolean = false) =
        compositeDisposable.add(useCase.get(refresh)
            .doOnSubscribe { posts.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { mapToPresentation(it) }
            .subscribe({ posts.setSuccess(it) }, { posts.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
