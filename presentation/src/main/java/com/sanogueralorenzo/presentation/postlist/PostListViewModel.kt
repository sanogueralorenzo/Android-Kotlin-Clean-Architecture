package com.sanogueralorenzo.presentation.postlist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanogueralorenzo.domain.usecase.UsersPostsUseCase
import com.sanogueralorenzo.presentation.StateData
import com.sanogueralorenzo.presentation.model.PostItem
import com.sanogueralorenzo.presentation.model.PostItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel @Inject constructor(private val useCase: UsersPostsUseCase,
                                            private val mapper: PostItemMapper) : ViewModel() {

    val state: MutableLiveData<StateData> = MutableLiveData()
    val posts: MutableLiveData<List<PostItem>> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        get()
    }

    fun get(refresh: Boolean = false) =
            compositeDisposable.add(useCase.get(refresh)
                    .doOnSubscribe { state.postValue(StateData.Loading) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe({
                        state.postValue(StateData.Success)
                        posts.postValue(it)
                    }, { state.postValue(StateData.Error(it)) }))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
