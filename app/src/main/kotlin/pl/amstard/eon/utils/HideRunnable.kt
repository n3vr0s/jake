package pl.amstard.eon.utils

import android.view.View
import java.lang.ref.WeakReference

class HideRunnable : Runnable {

    private val viewWeakReference: WeakReference<View>

    constructor(view: View) {
        viewWeakReference = WeakReference(view)
    }

    override fun run() {
        viewWeakReference.get().visible = false
    }

}