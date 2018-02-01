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

class UserDetailsPresenter @Inject constructor(private val userUseCase: UserUseCase,
                                               private val mapper: UserItemMapper) : Presenter<UserDetailsView>() {

    lateinit var userId: String

    override fun attachView(view: UserDetailsView) {
        super.attachView(view)
        userUseCase.userId = userId
        getUserUseCase()
    }

    private fun getUserUseCase() {
        addDisposable(userUseCase.execute()
                .subscribeOn(Schedulers.io())
                .map { mapper.mapToPresentation(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showUser(it) }, { }))
    }

    //TODO Implement https://github.com/terrakok/Cicerone for navigation
}
