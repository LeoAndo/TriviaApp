package com.reo.trivia.domain

sealed interface ApiResult<out T>
data class Success<out T>(val data: T) : ApiResult<T>
object Error : ApiResult<Nothing>
data class Failure<out T>(val error: Throwable): ApiResult<T>
