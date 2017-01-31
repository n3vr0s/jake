package pl.amstard.piwgroup.api

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import pl.amstard.piwgroup.annotation.NetworkInterceptors
import javax.inject.Singleton

@Module
class ReleaseApiModule {

    /**
     * We need logging and stetho only for debug builds since we don't want information
     * about server api to be logged on release buildS.
     */
    @Provides @Singleton @NetworkInterceptors
    fun provideNetworkInterceptors(): List<@JvmWildcard Interceptor> {
        return emptyList()
    }


}