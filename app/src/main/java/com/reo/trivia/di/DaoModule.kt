package com.reo.trivia.di

import android.content.Context
import androidx.room.Room
import com.reo.trivia.data.TriviaDatabase
import com.reo.trivia.data.dao.TriviaDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DaoModule {
    @Provides
    internal fun provideTriviaDatabase(context: Context): TriviaDatabase =
        Room.databaseBuilder(context, TriviaDatabase::class.java, "MyDataBase.db").build()

    @Provides
    @Singleton
    internal fun provideTriviaDao(database: TriviaDatabase): TriviaDao =
        database.triviaDao()
}