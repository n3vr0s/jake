package me.n3vr0s.auberginekotlin

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import javax.inject.Inject

abstract class AubergineCoordinatorLayout<P : AuberginePresenter<*,*>> : CoordinatorLayout, HasPresenter<P> {

    @Inject lateinit override var presenter : P

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        if(!isInEditMode){
            injectDependencies()
        }
    }

    protected abstract fun injectDependencies()
}
