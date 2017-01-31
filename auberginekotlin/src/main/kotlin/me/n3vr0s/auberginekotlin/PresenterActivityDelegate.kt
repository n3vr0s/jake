package me.n3vr0s.auberginekotlin

import android.os.Bundle

class PresenterActivityDelegate<P, V> : HasPresenter<P> where V : MvpView, P : AuberginePresenter<V, *> {

    override lateinit var presenter: P

    fun onCreate(presenter: P, savedInstanceState: Bundle?) {
        this.presenter = presenter
        presenter.onCreate(savedInstanceState)
    }

    fun onSaveInstanceState(outState: Bundle) {
        presenter.onSaveInstanceState(outState)
    }

    fun onDestroy() {
        presenter.onDestroy()
        presenter.unbindView()
    }

    fun onResume() {
        presenter.onResume()
    }

    fun onPause() {
        presenter.onPause()
    }

    fun onViewCreated(view: V) {
        presenter.bindView(view)
    }

}