package com.reo.trivia.data

import retrofit2.http.GET
import retrofit2.http.Path

internal interface TriviaApi {

    @GET("{month}/{date}")
    suspend fun getTrivia(
        @Path("month") month: String, @Path("date") date: String
    ): String

    @GET("random/trivia")
    suspend fun getRandomTrivia(): String

    @GET("random/year")
    suspend fun getRandomYear(): String

    @GET("random/math")
    suspend fun getRandomMath(): String

    @GET("random/date")
    suspend fun getRandomDate(): String
}