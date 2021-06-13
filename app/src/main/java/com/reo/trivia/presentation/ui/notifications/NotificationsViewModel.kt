package com.reo.trivia.presentation.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.reo.trivia.domain.TriviaService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NotificationsViewModel @Inject constructor(
    private val triviaService: TriviaService
) : ViewModel() {
    val resultTrivia: LiveData<List<String>> = liveData(context = viewModelScope.coroutineContext) {
        runCatching {
            triviaService.getAllTriviaList().collect { emit(it.map { entity -> entity.text }) }
        }.onFailure {
            Log.e("NotificationsViewModel", "flow error: " + it.message)
        }
    }
}