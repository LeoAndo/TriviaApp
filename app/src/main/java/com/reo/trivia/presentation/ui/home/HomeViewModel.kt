package com.reo.trivia.presentation.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.reo.trivia.domain.TriviaService
import com.reo.trivia.presentation.common.getDateValue
import com.reo.trivia.presentation.common.getMonthValue
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
    private val triviaService: TriviaService,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {
    val selectedMonthValue = ObservableField<String>()
    val selectedDateValue = ObservableField<String>()
    val monthDropdownValues = ObservableField(getMonthValue())
    val dateDropdownValues = ObservableField(getDateValue())

    private val _resultTrivia = MutableLiveData<String>()
    val resultTrivia: LiveData<String> = _resultTrivia

    internal fun getTrivia() {
        // viewModelScopeなので、viewModelの破棄時に コルーチンの処理はキャンセルされる
        // エラー時は、coroutineExceptionHandlerの中に入る.
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = triviaService.getTrivia(
                selectedMonthValue.get().orEmpty(),
                selectedDateValue.get().orEmpty()
            )
            _resultTrivia.value = result
        }
    }
}