package com.sanogueralorenzo.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface IView

interface IPresenter<in V : IView> {
    fun attachView(view: V)
    fun detachView()
}

open class Presenter<V : IView> : IPresenter<V> {

    var view: V? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        clearDisposable()
        this.view = null
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposable() {
        compositeDisposable.clear()
    }
}
