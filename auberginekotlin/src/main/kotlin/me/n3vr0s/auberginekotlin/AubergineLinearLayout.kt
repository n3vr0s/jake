package me.n3vr0s.auberginekotlin

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import javax.inject.Inject

abstract class AubergineLinearLayout<P : AuberginePresenter<*, *>> : LinearLayoutCompat, HasPresenter<P> {

    @Inject lateinit override var presenter: P

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
                                                                                    attrs,
                                                                                    defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!isInEditMode) {
            injectDependencies()
        }
    }

    open protected fun injectDependencies() {
    }
}
