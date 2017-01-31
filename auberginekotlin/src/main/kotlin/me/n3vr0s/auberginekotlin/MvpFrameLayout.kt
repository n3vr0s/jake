package me.n3vr0s.auberginekotlin

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import javax.inject.Inject

abstract class MvpFrameLayout<P : AuberginePresenter<*,*>> : FrameLayout, HasPresenter<P> {

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
