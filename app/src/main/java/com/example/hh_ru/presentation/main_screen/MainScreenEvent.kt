package com.example.hh_ru.presentation.main_screen

import com.example.hh_ru.domain.model.Vacancy

sealed class MainScreenEvent {
    object OnVacancyClick: MainScreenEvent()
    data class OnSearchFieldValueChange(val newSearchFieldValue: String): MainScreenEvent()
    data class OnLikeIconClick(val vacancy: Vacancy, val isFavorite: Boolean): MainScreenEvent()
    object OnMoreButtonClick: MainScreenEvent()
    object OnTriggerGettingFavoritesFromLaunchEffect: MainScreenEvent()
}