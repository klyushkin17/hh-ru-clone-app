package com.example.hh_ru.data.remote

import com.example.hh_ru.data.remote.dto.OfferListDto
import com.example.hh_ru.data.remote.dto.VacancyDto
import com.example.hh_ru.data.remote.dto.VacancyListDto
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface HhRuApi {
    @GET("vacancies")
    suspend fun getVacancies(): VacancyListDto

    @GET("offers")
    suspend fun getOffers(): OfferListDto

    companion object {
        const val BASE_URL = "https://hh-ru-clone-data.onrender.com/"
    }
}