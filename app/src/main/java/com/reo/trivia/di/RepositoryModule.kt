package com.reo.trivia.di

import com.reo.trivia.data.TriviaRemoteDataSource
import com.reo.trivia.data.TriviaLocalDataSource
import com.reo.trivia.domain.repsitory.TriviaLocalRepository
import com.reo.trivia.domain.repsitory.TriviaRemoteRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindTriviaRepository(dataSource: TriviaRemoteDataSource): TriviaRemoteRepository

    @Binds
    internal abstract fun bindTriviaLocalRepository(dataSource: TriviaLocalDataSource): TriviaLocalRepository
}