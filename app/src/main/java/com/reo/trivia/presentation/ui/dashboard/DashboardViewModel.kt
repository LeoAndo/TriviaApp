package com.reo.trivia.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reo.trivia.domain.TriviaService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val triviaService: TriviaService,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {
    private val _resultTrivia = MutableLiveData<String>()
    val resultTrivia: LiveData<String> = _resultTrivia

    internal fun getRandomMonthTrivia() {
        getTrivia { triviaService.getRandomMath() }
    }

    internal fun getRandomTrivia() {
        getTrivia { triviaService.getRandomTrivia() }
    }

    internal fun getRandomYearTrivia() {
        getTrivia { triviaService.getRandomYear() }
    }

    internal fun getRandomDateTrivia() {
        getTrivia { triviaService.getRandomDate() }
    }

    private fun <T> getTrivia(apiCall: suspend () -> T) {
        // 特定のスコープの全てのジョブを明示的にキャンセルする場合は、以下で良さそう.
        // viewModelScope.coroutineContext.cancelChildren()
        viewModelScope.launch(coroutineExceptionHandler) {
            val resultTrivia = apiCall.invoke()
            _resultTrivia.value = resultTrivia.toString()
        }
    }

}