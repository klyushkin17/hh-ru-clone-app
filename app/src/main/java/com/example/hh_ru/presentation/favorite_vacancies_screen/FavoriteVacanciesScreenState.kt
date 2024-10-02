package com.example.hh_ru.presentation.favorite_vacancies_screen

import com.example.hh_ru.domain.model.FavoriteVacancy

data class FavoriteVacanciesScreenState(
    val favoriteVacanciesList: List<FavoriteVacancy> = emptyList(),
    val favoriteVacanciesIdsMap: Map<String, Boolean> = emptyMap(),
)
