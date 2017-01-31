package pl.amstard.eon.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.threeten.bp.Clock
import pl.amstard.eon.annotation.ApplicationContext
import pl.amstard.eon.annotation.GithubUrl
import pl.amstard.eon.annotation.NetworkInterceptors
import pl.amstard.eon.communications.CommunicationManager
import pl.amstard.eon.communications.GithubApi
import pl.amstard.eon.utils.RxErrorHandlingCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.Scheduler
import rx.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Core dagger module that provides most core dependencies that the app needs.
 */
@Module
class CoreApiModule {

    private val GITHUB_URL = "https://api.github.com/"

    @Provides @GithubUrl
    fun providePiwgroupUrl(): String = GITHUB_URL

    @Provides @Singleton @Named("Api")
    fun provideApiClient(client: OkHttpClient,
                         @NetworkInterceptors networkInterceptors: List<Interceptor>): OkHttpClient {
        val okClient = client.newBuilder()
        okClient.networkInterceptors().addAll(networkInterceptors)
        return okClient.build()
    }

    @Provides @Singleton
    fun provideScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides @Singleton
    fun provideRetrofit(@Named("Api") apiClient: OkHttpClient,
                        gson: Gson,
                        @GithubUrl githubUrl: String,
                        scheduler: Scheduler): Retrofit {
        return Retrofit.Builder()
            .client(apiClient)
            .baseUrl(HttpUrl.parse(githubUrl))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.createWithScheduler(scheduler))
            .build()
    }

    @Provides @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides @Singleton
    fun provideGson() : Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides @Singleton
    fun provideClock(): Clock = Clock.systemDefaultZone()

    @Provides @Singleton
    fun providePicasso(@ApplicationContext context: Context, client: OkHttpClient) : Picasso{
        val customDownloader = client.newBuilder()
            .build()
        return Picasso.Builder(context).downloader(OkHttp3Downloader(customDownloader)).build()
    }

    @Provides @Singleton
    fun provideCommunicationManager(githubApi: GithubApi) : CommunicationManager {
        return CommunicationManager(githubApi)
    }

}