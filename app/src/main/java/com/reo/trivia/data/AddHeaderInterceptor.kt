package com.reo.trivia.data

import com.reo.trivia.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val CONTENT_TYPE = "Content-Type"

/**
 * API通信に必要なヘッダーを追加するインターセプター.
 */
class AddHeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        val originalUrl = request.url.toString()

        if (originalUrl.contains(BuildConfig.API_DOMAIN) && request.header(CONTENT_TYPE)
                .isNullOrEmpty()
        ) {
            requestBuilder.addHeader(CONTENT_TYPE, "text/plain;charset=UTF-8")
        }
        return chain.proceed(requestBuilder.build())
    }
}