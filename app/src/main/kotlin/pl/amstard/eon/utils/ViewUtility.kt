package pl.amstard.eon.utils

import android.support.annotation.StringRes
import android.view.View

fun View.getString(@StringRes stringResId: Int) : String {
    return resources.getString(stringResId)
}

class ViewUtility {

    companion object {

        fun hideViewAfterGivenTimeInSeconds(view: View, second: Int){
            view.postDelayed(HideRunnable(view), (second * 1000).toLong())
        }

    }

}