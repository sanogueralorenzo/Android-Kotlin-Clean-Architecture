package com.sanogueralorenzo.presentation.postlist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanogueralorenzo.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.presentation.Data
import com.sanogueralorenzo.presentation.DataState
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel @Inject constructor(
    private val useCase: UsersPostsUseCase,
    private val mapper: PostItemMapper
) : ViewModel() {

    val posts = MutableLiveData<Data<List<PostItem>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        get()
    }

    fun get(refresh: Boolean = false) =
            compositeDisposable.add(useCase.get(refresh)
                    .doOnSubscribe { posts.postValue(Data(dataState = DataState.LOADING, data = posts.value?.data, message = null)) }
                    .subscribeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe({
                        posts.postValue(Data(dataState = DataState.SUCCESS, data = it, message = null))
                    }, { posts.postValue(Data(dataState = DataState.ERROR, data = posts.value?.data, message = it.message)) }))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
