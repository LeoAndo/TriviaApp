package com.reo.trivia.di

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.reo.trivia.domain.exception.BadRequestErrorException
import com.reo.trivia.domain.exception.NetworkErrorException
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    fun bindCoroutineExceptionHandler(app: Application): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            Log.e("ExceptionHandler", "hashcode: ${hashCode()}")
            when (throwable) {
                is NetworkErrorException -> {
                    Toast.makeText(app, "NetworkErrorException時の処理を行う", Toast.LENGTH_SHORT).show()
                }
                is BadRequestErrorException -> {
                    Toast.makeText(app, "BadRequestErrorException時の処理を行う", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(app, throwable.localizedMessage ?: "error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}