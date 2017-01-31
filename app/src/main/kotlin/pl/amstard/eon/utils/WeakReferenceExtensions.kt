package pl.amstard.eon.utils

import java.lang.ref.WeakReference

fun <T> WeakReference<T>.nn() : T {
    return this.get()
}

fun <T> WeakReference<T>.nn(predicate: (t: T) -> T) : T? {
    if(this.get() != null){
        predicate.invoke(this.get())
    }
    return this.get()
}

fun <T> WeakReference<T>.invoke(predicate: (t: T) -> Unit) {
    if(this.get() != null){
        predicate.invoke(this.get())
    }
}