package com.sanogueralorenzo.presentation.userdetails

import com.sanogueralorenzo.domain.usecase.UserUseCase
import com.sanogueralorenzo.presentation.IView
import com.sanogueralorenzo.presentation.Presenter
import com.sanogueralorenzo.presentation.model.UserItem
import com.sanogueralorenzo.presentation.model.UserItemMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface UserDetailsView : IView {
    fun showUser(user: UserItem)
}

class UserDetailsPresenter @Inject constructor(private val useCase: UserUseCase,
                                               private val mapper: UserItemMapper) : Presenter<UserDetailsView>() {

    fun get(userId: String, refresh: Boolean = false) {
        addDisposable(useCase.get(userId, refresh)
                .subscribeOn(Schedulers.io())
                .map { mapper.mapToPresentation(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showUser(it) }, { }))
    }
}
