package pl.amstard.eon.utils

import android.support.v7.widget.LinearLayoutManager
import android.view.View

fun LinearLayoutManager.findFirstVisibleView(): View? {
    return this.findViewByPosition(this.findFirstVisibleItemPosition())
}

fun LinearLayoutManager.findLastVisibleView(): View? {
    return this.findViewByPosition(this.findLastVisibleItemPosition())
}
