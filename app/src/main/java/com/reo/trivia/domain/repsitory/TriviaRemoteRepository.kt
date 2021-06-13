package com.reo.trivia.domain.repsitory

interface TriviaRemoteRepository {
    suspend fun getTrivia(month: String, date: String): String
    suspend fun getRandomTrivia(): String
    suspend fun getRandomYear(): String
    suspend fun getRandomMath(): String
    suspend fun getRandomDate(): String
}