package me.n3vr0s.auberginekotlin

import android.os.Bundle
import timber.log.Timber
import java.lang.ref.WeakReference

abstract class AuberginePresenter<V : MvpView, VM> : MvpPresenter<V> where VM : AubergineViewModel {

    var viewWeakRef: WeakReference<V>? = null
    lateinit var viewModel: VM

    override fun bindView(view: V) {
        this.viewWeakRef = WeakReference(view)
        Timber.v("presenter %s bindView %s", this, view)
    }

    override fun unbindView() {
        this.viewWeakRef = null
    }

    override fun onCreate(bundle: Bundle?) {
        viewModel = prepareViewModel()
        //        Icepick.restoreInstanceState(viewModel, bundle);
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        //        Icepick.saveInstanceState(viewModel, bundle);
    }

    protected abstract fun prepareViewModel(): VM

    val isViewAttached: Boolean
        get() = viewWeakRef != null && viewWeakRef!!.get() != null


}

fun <T, V : MvpView> AuberginePresenter<V, *>.runOnView(body: (view: V) -> T): T? {
    val simpleView = viewWeakRef?.get()
    if (simpleView != null) {
        return body.invoke(simpleView)
    } else {
        return null
    }
}

fun <V : MvpView> AuberginePresenter<V, *>.runOnView(body: (view: V) -> Unit) {
    val simpleView = viewWeakRef?.get()
    if (simpleView != null) {
        body.invoke(simpleView)
    }
}

val <V : MvpView> AuberginePresenter<V,*>.view: V?
    get() = viewWeakRef?.get()