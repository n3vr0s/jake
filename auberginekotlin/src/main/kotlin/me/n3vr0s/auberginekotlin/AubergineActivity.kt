package me.n3vr0s.auberginekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import javax.inject.Inject

abstract class AubergineActivity<P, V, C> : AppCompatActivity(), HasPresenter<P> where V : MvpView,
    P : AuberginePresenter<V,*>, C : Any {

    @Inject override lateinit var presenter: P

    protected var presenterDelegate: PresenterActivityDelegate<P,V> = PresenterActivityDelegate()
    var component: C? = null
        protected set

    @Suppress("UNCHECKED_CAST")
    public override fun onCreate(savedInstanceState: Bundle?) {
        var savedInstanceStateWithArguments = savedInstanceState
        super.onCreate(savedInstanceStateWithArguments)

        if(lastCustomNonConfigurationInstance as? C != null){
            component = lastNonConfigurationInstance as C
        }else{
            component = createDaggerComponent()
        }

        injectDependencies()

        val view = createView()

        val arguments = intent.extras
        if (arguments != null) {
            if (savedInstanceStateWithArguments == null) {
                savedInstanceStateWithArguments = arguments
            } else {
                savedInstanceStateWithArguments.putAll(arguments)
            }
        }


//
//        val reteinedPresenter = lastCustomNonConfigurationInstance
//        if(reteinedPresenter as? P != null){
//            presenter = reteinedPresenter as P
//        }
        setContentView(view)

        presenterDelegate.onCreate(presenter, savedInstanceStateWithArguments)
        presenterDelegate.onViewCreated(getMvpView())

    }

    protected abstract fun createDaggerComponent(): C

    open protected fun injectDependencies(){}

    protected abstract fun createView(): View

    protected abstract fun getMvpView(): V

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenterDelegate.onSaveInstanceState(outState)
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

    override fun onRetainCustomNonConfigurationInstance() : C? {
        return component
    }

}
