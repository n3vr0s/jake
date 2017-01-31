package me.n3vr0s.auberginekotlin

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class AubergineFragment<P, V> : Fragment(), HasPresenter<P> where V : MvpView,
P : AuberginePresenter<V, *> {

    @Inject override lateinit var presenter: P

    private var presenterDelegate = PresenterFragmentDelegate<P, V>()
    protected var activityWeakRef: WeakReference<out Activity>? = null
    lateinit protected var viewWeakRef: WeakReference<View>

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (arguments != null) {
            savedInstanceState?.putAll(arguments)
        }

        injectDependencies()

        val view = createView(savedInstanceState)
        viewWeakRef = WeakReference(view)

        presenterDelegate.onCreate(presenter, savedInstanceState ?: arguments)
        presenterDelegate.onViewCreated(getMvpView())

        return view
    }

    /**
     * View has to implement AubergineView interface
     * @return
     */
    protected abstract fun createView(savedInstanceState: Bundle?): View

    open protected fun injectDependencies(){}

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenterDelegate.onSaveInstanceState(outState!!)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        activityWeakRef = WeakReference<Activity>(activity)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        activityWeakRef = null
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate.onResume()
    }

    override fun onPause() {
        presenterDelegate.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        presenterDelegate.onDestroy()
        super.onDestroy()
    }

    override fun onDestroyView() {
        presenterDelegate.onDestroyView()
        super.onDestroyView()
    }

    protected abstract fun getMvpView(): V
}
