package com.reo.trivia.di

import android.content.Context
import android.util.Log
import com.reo.trivia.BuildConfig
import com.reo.trivia.data.AddHeaderInterceptor
import com.reo.trivia.data.TriviaApi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val TIMEOUT_SEC: Long = 15
    private const val CACHE_SIZE: Long = 10 * 1024 * 1024

    @Singleton
    @Provides
    internal fun provideOkHttpClientCache(context: Context): Cache {
        val file = File(context.cacheDir, "template")
        return Cache(file, CACHE_SIZE)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: AddHeaderInterceptor,
        cache: Cache
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(interceptor)
        if (BuildConfig.DEBUG) builder.addInterceptor(createHttpLoggingInterceptor())
        return builder.build()
    }

    @Singleton
    @Provides
    internal fun provideTriviaApi(
        okHttpClient: Lazy<OkHttpClient>
    ): TriviaApi {
        return Retrofit.Builder()
            .callFactory(object : okhttp3.Call.Factory {
                override fun newCall(request: Request): okhttp3.Call {
                    return okHttpClient.get().newCall(request)
                }
            })
            .baseUrl(BuildConfig.API_DOMAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TriviaApi::class.java)
    }

    // http Log.
    private fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp", message)
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}