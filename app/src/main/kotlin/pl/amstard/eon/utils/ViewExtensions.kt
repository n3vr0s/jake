package pl.amstard.eon.utils

import android.view.View

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(isVisible: Boolean) = if (isVisible) visibility = View.VISIBLE else visibility = View.GONE