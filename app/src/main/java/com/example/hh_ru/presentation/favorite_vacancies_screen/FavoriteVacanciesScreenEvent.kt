package com.example.hh_ru.presentation.favorite_vacancies_screen

import com.example.hh_ru.domain.model.FavoriteVacancy
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.presentation.main_screen.MainScreenEvent

sealed class FavoriteVacanciesScreenEvent {
    data class OnLikeIconClick(val vacancy: FavoriteVacancy): FavoriteVacanciesScreenEvent()
    object OnCloseComposition: FavoriteVacanciesScreenEvent()
    object OnVacancyClick: FavoriteVacanciesScreenEvent()
}