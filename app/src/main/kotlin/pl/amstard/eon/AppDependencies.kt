package pl.amstard.eon

import android.content.Context
import com.squareup.picasso.Picasso
import com.tbruyelle.rxpermissions.RxPermissions
import pl.amstard.eon.annotation.ApplicationContext
import pl.amstard.eon.annotation.GithubUrl

interface AppDependencies {

    @ApplicationContext fun provideApplicationContext(): Context
    @GithubUrl fun provideEonUrl(): String
    fun providePicasso(): Picasso
    fun provideRxPermissions(): RxPermissions
}
