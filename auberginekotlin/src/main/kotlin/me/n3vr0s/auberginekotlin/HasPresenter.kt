package me.n3vr0s.auberginekotlin

interface HasPresenter<P> where P : AuberginePresenter<*,*> {

    var presenter : P

}