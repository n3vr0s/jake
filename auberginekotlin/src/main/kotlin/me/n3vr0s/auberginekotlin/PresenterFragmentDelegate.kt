package me.n3vr0s.auberginekotlin

import android.os.Bundle

class PresenterFragmentDelegate<P, V> : HasPresenter<P> where V : MvpView, P : AuberginePresenter<V, *> {

    override lateinit var presenter: P

    var isFragmentGoingToBeDestroyed = true

    fun onCreate(presenter: P, savedInstanceState: Bundle?) {
        this.presenter = presenter
        presenter.onCreate(savedInstanceState)
    }

    fun onSaveInstanceState(outState: Bundle) {
        isFragmentGoingToBeDestroyed = true
        presenter.onSaveInstanceState(outState)
    }

    fun onDestroy() {
        if (!isFragmentGoingToBeDestroyed) {
            presenter.onDestroy()
        }
    }

    fun onResume() {
        presenter.onResume()
        isFragmentGoingToBeDestroyed = false
    }

    fun onPause() {
        presenter.onPause()
    }

    fun onViewCreated(view: V) {
        presenter.bindView(view)
    }

    fun onDestroyView() {
        presenter.unbindView()
    }

}