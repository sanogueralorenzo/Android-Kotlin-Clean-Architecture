package com.sanogueralorenzo.presentation.userdetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanogueralorenzo.domain.usecase.UserUseCase
import com.sanogueralorenzo.presentation.model.UserItem
import com.sanogueralorenzo.presentation.model.UserItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val useCase: UserUseCase,
                                               private val mapper: UserItemMapper) : ViewModel() {

    val user = MutableLiveData<UserItem>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var userId: String? = null
        set(value) {
            if (field == null) {
                field = value
                get()
            }
        }

    fun get(refresh: Boolean = false) =
            compositeDisposable.add(useCase.get(userId!!, refresh)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe({ user.postValue(it) }, { }))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
