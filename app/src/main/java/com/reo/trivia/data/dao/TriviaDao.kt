package com.reo.trivia.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reo.trivia.data.entity.TriviaEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class TriviaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTriviaTable(trivia: TriviaEntity)

    // Trivia情報を全件取得する.
    @Query("SELECT * FROM TriviaTable")
    abstract fun findAllTriviaList(): Flow<List<TriviaEntity>>
}