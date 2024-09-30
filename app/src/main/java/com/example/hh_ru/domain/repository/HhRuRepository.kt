package com.example.hh_ru.domain.repository

import com.example.hh_ru.data.local.entities.FavoriteVacancyEntity
import com.example.hh_ru.domain.model.FavoriteVacancy
import com.example.hh_ru.domain.model.OfferList
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.domain.model.VacancyList
import com.example.hh_ru.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HhRuRepository {

    suspend fun getVacancies(): Flow<Resource<VacancyList>>

    suspend fun getOffers(): Flow<Resource<OfferList>>

    suspend fun insertVacancyToFavorite(vacancy: FavoriteVacancyEntity)

    suspend fun deleteVacancyFromFavorites(vacancyId: String)

    suspend fun getFavoriteVacancies(): Flow<List<FavoriteVacancyEntity>>
}