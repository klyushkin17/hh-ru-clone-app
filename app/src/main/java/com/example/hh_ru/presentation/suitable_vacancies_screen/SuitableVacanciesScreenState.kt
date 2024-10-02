package com.example.hh_ru.presentation.suitable_vacancies_screen

import com.example.hh_ru.domain.model.VacancyList

data class SuitableVacanciesScreenState(
    val vacancyList: VacancyList = VacancyList(vacancyList = emptyList()),
    val searchFiledValue: String = "",
    val favoriteVacancyIds: List<String> = emptyList(),
)