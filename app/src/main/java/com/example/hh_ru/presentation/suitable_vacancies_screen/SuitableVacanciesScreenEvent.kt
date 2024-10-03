package com.example.hh_ru.presentation.suitable_vacancies_screen

import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.presentation.main_screen.MainScreenEvent

sealed class SuitableVacanciesScreenEvent {
    object OnVacancyClick: SuitableVacanciesScreenEvent()
    data class OnSearchFieldValueChange(val newSearchFieldValue: String): SuitableVacanciesScreenEvent()
    data class OnLikeIconClick(val vacancy: Vacancy, val isFavorite: Boolean): SuitableVacanciesScreenEvent()
    object OnBackArrowIconClick: SuitableVacanciesScreenEvent()
    object OnTriggerGettingFavoritesFromLaunchEffect: SuitableVacanciesScreenEvent()
    object GoToFavoritesTEMP: SuitableVacanciesScreenEvent()
}