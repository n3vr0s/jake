package pl.amstard.eon.base

import me.n3vr0s.auberginekotlin.AubergineViewModel
import me.n3vr0s.auberginekotlin.MvpView
import me.n3vr0s.auberginekotlin.RxAuberginePresenter
import org.greenrobot.eventbus.EventBus
import pl.amstard.eon.communications.responses.ErrorResponse
import pl.amstard.eon.events.UnauthorizedRetrofitErrorEvent
import pl.amstard.eon.utils.RetrofitException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

abstract class RxAuberginePresenterImpl<V : MvpView, VM> : RxAuberginePresenter<V, VM>() where VM : AubergineViewModel{

    private fun defaultErrorHandler(throwable: Throwable, customErrorHandler: ((ErrorResponse)->Unit)? = null) {
        throwable.printStackTrace()
        if (throwable is RetrofitException) {
            if (throwable.kind == RetrofitException.Kind.HTTP) {
                if (throwable.code == 401) {
                    EventBus.getDefault().post(UnauthorizedRetrofitErrorEvent())
                } else {
                    try {
                        val realError = throwable.getErrorBodyAs(ErrorResponse::class.java)
//                        Timber.v(realError.error?.message ?: "")
                        customErrorHandler?.invoke(realError)
                    } catch (e: Exception) {

                    }
                }
            }
        }else{
            throw throwable
        }
    }

    protected fun <T> addSubscriberToSubscription(observable: Observable<T>, successAction: (T)->Unit, customErrorHandler: ((ErrorResponse) -> Unit)? = null){
        val subscription = observable.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { successAction.invoke(it) },
                    { defaultErrorHandler(it, customErrorHandler) }
            )
        compositeSubscription?.let {
            it.add(subscription)
        }
    }

}
