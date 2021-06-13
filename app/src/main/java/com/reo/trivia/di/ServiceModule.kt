package com.reo.trivia.di

import com.reo.trivia.domain.TriviaService
import com.reo.trivia.domain.TriviaServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {
    @Binds
    abstract fun bindTodoService(impl: TriviaServiceImpl): TriviaService
}