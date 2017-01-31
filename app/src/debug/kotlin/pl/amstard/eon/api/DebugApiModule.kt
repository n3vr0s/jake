package pl.amstard.eon.api

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import pl.amstard.eon.annotation.NetworkInterceptors
import javax.inject.Singleton

@Module
class DebugApiModule {

    @Provides @Singleton @NetworkInterceptors
    fun provideNetworkInterceptors(): List<@JvmWildcard Interceptor> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//        val okLogInterceptor = OkLogInterceptor.builder().build()
        return arrayListOf(loggingInterceptor)
    }
}