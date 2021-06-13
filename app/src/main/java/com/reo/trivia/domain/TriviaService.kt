package com.reo.trivia.domain

import com.reo.trivia.domain.repsitory.TriviaLocalRepository
import com.reo.trivia.domain.repsitory.TriviaRemoteRepository
import com.reo.trivia.data.entity.TriviaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TriviaService {
    suspend fun getTrivia(month: String, date: String): String
    suspend fun getRandomTrivia(): String
    suspend fun getRandomYear(): String
    suspend fun getRandomMath(): String
    suspend fun getRandomDate(): String
    fun getAllTriviaList(): Flow<List<TriviaEntity>>
}

class TriviaServiceImpl @Inject constructor(
    private val triviaRemoteRepository: TriviaRemoteRepository,
    private val triviaLocalRepository: TriviaLocalRepository
) : TriviaService {
    override suspend fun getTrivia(month: String, date: String): String {
        val apiResult = triviaRemoteRepository.getTrivia(month, date)
        triviaLocalRepository.insertTriviaInfo(apiResult)
        return apiResult
    }

    override suspend fun getRandomTrivia(): String {
        val apiResult = triviaRemoteRepository.getRandomTrivia()
        triviaLocalRepository.insertTriviaInfo(apiResult)
        return apiResult
    }

    override suspend fun getRandomYear(): String {
        val apiResult = triviaRemoteRepository.getRandomYear()
        triviaLocalRepository.insertTriviaInfo(apiResult)
        return apiResult
    }

    override suspend fun getRandomMath(): String {
        val apiResult = triviaRemoteRepository.getRandomMath()
        triviaLocalRepository.insertTriviaInfo(apiResult)
        return apiResult
    }

    override suspend fun getRandomDate(): String {
        val apiResult = triviaRemoteRepository.getRandomDate()
        triviaLocalRepository.insertTriviaInfo(apiResult)
        return apiResult
    }

    override fun getAllTriviaList(): Flow<List<TriviaEntity>> {
        return triviaLocalRepository.getAllTriviaList()
    }
}