package com.reo.trivia.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reo.trivia.data.dao.TriviaDao
import com.reo.trivia.data.entity.TriviaEntity

@Database(entities = [TriviaEntity::class], version = 1, exportSchema = false)
internal abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao
}