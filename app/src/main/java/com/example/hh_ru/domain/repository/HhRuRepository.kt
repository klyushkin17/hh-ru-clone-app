package com.example.hh_ru.domain.repository

import com.example.hh_ru.domain.model.OfferList
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.domain.model.VacancyList
import com.example.hh_ru.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HhRuRepository {

    suspend fun getVacancies(): Flow<Resource<VacancyList>>

    suspend fun getVacancyInfo(vacancyId: String): Flow<Resource<Vacancy>>

    suspend fun getOffers(): Flow<Resource<OfferList>>

    //suspend fun getFavoriteVacancies()
}