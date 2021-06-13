package com.reo.trivia.data

import com.reo.trivia.domain.repsitory.TriviaRemoteRepository
import com.reo.trivia.domain.apiCall
import javax.inject.Inject

internal class TriviaRemoteDataSource @Inject constructor(
    private val api: TriviaApi
) : TriviaRemoteRepository {
    override suspend fun getTrivia(month: String, date: String): String {
        return apiCall { api.getTrivia(month, date) }
    }

    override suspend fun getRandomTrivia(): String {
        return apiCall { api.getRandomTrivia() }
    }

    override suspend fun getRandomYear(): String {
        return apiCall { api.getRandomYear() }
    }

    override suspend fun getRandomMath(): String {
        return apiCall { api.getRandomMath() }
    }

    override suspend fun getRandomDate(): String {
        return apiCall { api.getRandomDate() }
    }
}