package me.n3vr0s.auberginekotlin

import android.os.Bundle
import rx.subscriptions.CompositeSubscription

abstract class RxAuberginePresenter<V : MvpView, VM> : AuberginePresenter<V, VM>() where VM : AubergineViewModel {

    var compositeSubscription : CompositeSubscription? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        compositeSubscription = CompositeSubscription()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription?.clear()
    }

}

