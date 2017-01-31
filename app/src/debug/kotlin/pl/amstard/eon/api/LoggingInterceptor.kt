package pl.amstard.eon.api

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import org.threeten.bp.Clock
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interceptor to log network calls with their path, headers and times.
 */
@Singleton
class LoggingInterceptor

    @Inject constructor(private val clock: Clock) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startMs = clock.millis()
        Timber.v("Sending request %s%s", request.url(), prettyHeaders(request.headers()))

        val response = chain.proceed(request)

        val tookMs = clock.millis() - startMs
        Timber.v("Received response (%s) for %s in %sms%s", response.code(),
            response.request().url(),
            tookMs, prettyHeaders(response.headers()),
                request.body().toString())

        return response
    }

    private fun prettyHeaders(headers: Headers): String {
        if (headers.size() == 0) return ""

        val builder = StringBuilder()
        builder.append("\n  Headers:")

        for (i in 0..headers.size() - 1) {
            builder.append("\n    ").append(headers.name(i)).append(": ").append(headers.value(i))
        }

        return builder.toString()
    }
}
