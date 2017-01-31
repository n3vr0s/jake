package pl.amstard.eon

import android.content.Context
import com.tbruyelle.rxpermissions.RxPermissions
import dagger.Module
import dagger.Provides
import pl.amstard.eon.annotation.ApplicationContext
import javax.inject.Singleton

@Module class AppModule(val app: App) {

    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton @ApplicationContext
    fun provideApplicationContext(): Context = app

    @Provides @Singleton
    fun provideRxPermissions(): RxPermissions = RxPermissions.getInstance(app)
}
