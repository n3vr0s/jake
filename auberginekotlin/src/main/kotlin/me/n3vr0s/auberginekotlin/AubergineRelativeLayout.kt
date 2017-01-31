package me.n3vr0s.auberginekotlin

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import javax.inject.Inject

abstract class AubergineRelativeLayout<P : AuberginePresenter<*,*>> : RelativeLayout, HasPresenter<P> {

    @Inject lateinit override var presenter : P

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if(!isInEditMode){
            injectDependencies()
        }
    }

    protected abstract fun injectDependencies()
}
