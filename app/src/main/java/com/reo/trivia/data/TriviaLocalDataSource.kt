package com.reo.trivia.data

import com.reo.trivia.data.dao.TriviaDao
import com.reo.trivia.data.entity.TriviaEntity
import com.reo.trivia.domain.repsitory.TriviaLocalRepository
import com.reo.trivia.domain.dbCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TriviaLocalDataSource @Inject constructor(
    private val dao: TriviaDao
) : TriviaLocalRepository {
    override suspend fun insertTriviaInfo(text: String) {
        val entity = TriviaEntity(0, text)
        dbCall { dao.insertTriviaTable(entity) }
    }

    override fun getAllTriviaList(): Flow<List<TriviaEntity>> {
        return dao.findAllTriviaList()
    }
}