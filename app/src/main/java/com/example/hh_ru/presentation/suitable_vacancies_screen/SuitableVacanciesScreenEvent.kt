package com.example.hh_ru.presentation.suitable_vacancies_screen

import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.presentation.main_screen.MainScreenEvent

sealed class SuitableVacanciesScreenEvent {
    data class OnVacancyClick(val vacancy: Vacancy): SuitableVacanciesScreenEvent()
    data class OnSearchFieldValueChange(val newSearchFieldValue: String): SuitableVacanciesScreenEvent()
}