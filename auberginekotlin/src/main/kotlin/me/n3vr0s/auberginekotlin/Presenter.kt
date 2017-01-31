package me.n3vr0s.auberginekotlin

import android.os.Bundle

interface Presenter<V>  {

    fun onCreate(bundle: Bundle?)
    fun onSaveInstanceState(bundle: Bundle)
    fun onResume()
    fun onPause()
    fun onDestroy()
    fun bindView(view: V)
    fun unbindView()
}
