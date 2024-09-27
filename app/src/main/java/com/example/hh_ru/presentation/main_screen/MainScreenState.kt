package com.example.hh_ru.presentation.main_screen

import com.example.hh_ru.domain.model.OfferList
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.domain.model.VacancyList

data class MainScreenState(
    val vacancyList: VacancyList = VacancyList(vacancyList = emptyList()),
    val offerList: OfferList = OfferList(offerList = emptyList())
)
