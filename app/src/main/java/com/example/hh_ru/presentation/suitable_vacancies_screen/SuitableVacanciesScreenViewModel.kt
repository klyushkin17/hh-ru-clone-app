package com.example.hh_ru.presentation.suitable_vacancies_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.utils.Resource
import com.example.hh_ru.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SuitableVacanciesScreenViewModel @Inject constructor(
    private val hhRuRepository: HhRuRepository
): ViewModel() {

    init {
        getVacancies()
    }

    private val _state = MutableStateFlow(SuitableVacanciesScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SuitableVacanciesScreenEvent) {
        when(event) {
            is SuitableVacanciesScreenEvent.OnVacancyClick -> {

            }
            is SuitableVacanciesScreenEvent.OnSearchFieldValueChange -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        searchFiledValue = event.newSearchFieldValue
                    )
                }
            }
        }
    }

    private fun getVacancies() {
        viewModelScope.launch {
            hhRuRepository
                .getVacancies()
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { vacancies ->
                                _state.value = state.value.copy(
                                    vacancyList = vacancies
                                )
                            }
                        }
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                    }
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateString(dateString: String): String {
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, inputFormat)
        val outputFormat = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        return date.format(outputFormat)
    }

    fun formatDateStringLowVersion(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-d90d")
        val date = inputFormat.parse(dateString)
        val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))
        return outputFormat.format(date)
    }

    fun getPeopleDeclinationByNumber(numberOfPeople: String): String {
        val people_01_5_9 = "человек"
        val people_2_4 = "человека"
        when(numberOfPeople.last()) {
            '0' -> return people_01_5_9
            '1' -> return people_01_5_9
            '2' -> return people_2_4
            '3' -> return people_2_4
            '4' -> return people_2_4
            '5' -> return people_01_5_9
            '6' -> return people_01_5_9
            '7' -> return people_01_5_9
            '8' -> return people_01_5_9
            '9' -> return people_01_5_9
            else -> return people_01_5_9
        }
    }

    fun getWatchDeclinationByNumber(numberOfPeople: String): String {
        val watch_1 = "просматривает"
        val watch_0_2_9= "просматривают"
        when(numberOfPeople.last()) {
            '0' -> return watch_0_2_9
            '1' -> return watch_1
            '2' -> return watch_0_2_9
            '3' -> return watch_0_2_9
            '4' -> return watch_0_2_9
            '5' -> return watch_0_2_9
            '6' -> return watch_0_2_9
            '7' -> return watch_0_2_9
            '8' -> return watch_0_2_9
            '9' -> return watch_0_2_9
            else -> return watch_0_2_9
        }
    }

    fun getVacancyDeclinationByNumber(numberOfVacancies: String): String {
        val vacancy_01_5_9 = "вакансий"
        val vacancy_2_4 = "вакансии"
        when(numberOfVacancies.last()) {
            '0' -> return vacancy_01_5_9
            '1' -> return vacancy_01_5_9
            '2' -> return vacancy_2_4
            '3' -> return vacancy_2_4
            '4' -> return vacancy_2_4
            '5' -> return vacancy_01_5_9
            '6' -> return vacancy_01_5_9
            '7' -> return vacancy_01_5_9
            '8' -> return vacancy_01_5_9
            '9' -> return vacancy_01_5_9
            else -> return vacancy_01_5_9
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}