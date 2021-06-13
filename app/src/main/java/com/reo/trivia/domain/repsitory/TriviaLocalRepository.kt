package com.reo.trivia.domain.repsitory

import com.reo.trivia.data.entity.TriviaEntity
import kotlinx.coroutines.flow.Flow

interface TriviaLocalRepository {
    suspend fun insertTriviaInfo(text: String)
    fun getAllTriviaList(): Flow<List<TriviaEntity>>
}