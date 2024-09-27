package com.example.hh_ru.data.remote

import com.example.hh_ru.data.remote.dto.OfferListDto
import com.example.hh_ru.data.remote.dto.VacancyDto
import com.example.hh_ru.data.remote.dto.VacancyListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HhRuApi {
    @GET("vacancies")
    suspend fun getVacancies(): VacancyListDto

    @GET("offers")
    suspend fun getOffers(): OfferListDto

    @GET("vacancies/{id}")
    suspend fun getVacancyById(
        @Path("id") vacancyId: String
    ): VacancyDto

    companion object {
        const val BASE_URL = "https://hh-ru-clone-data-1.onrender.com/"
    }
}