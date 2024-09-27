package com.example.hh_ru.data.remote.dto

import com.squareup.moshi.Json

data class VacancyDto(
    @field:Json(name = "id") val vacancyId: String,
    @field:Json(name = "lookingNumber") val lookingNumber: Int,
    @field:Json(name = "title") val vacancyTitle: String,
    @field:Json(name = "address") val address: AddressInfoDto,
    @field:Json(name = "company") val company: String,
    @field:Json(name = "experience") val experience: ExperienceInfoDto,
    @field:Json(name = "publishedDate") val publishedDate: String,
    @field:Json(name = "isFavorite") val isFavorites: Boolean,
    @field:Json(name = "salary") val salary: SalaryInfoDto,
    //@field:Json(name = "schedules") val schedules: List<String>,
    @field:Json(name = "appliedNumber") val appliedNumber: Int,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "responsibilities") val responsibilities: String,
    //@field:Json(name = "question") val question: List<String>,
)

data class AddressInfoDto(
    @field:Json(name = "town") val town: String,
    @field:Json(name = "street") val street: String,
    @field:Json(name = "house") val house: String,
)

data class ExperienceInfoDto(
    @field:Json(name = "previewText") val previewText: String,
    @field:Json(name = "text") val text: String,
)

data class SalaryInfoDto(
    @field:Json(name = "short") val short: String?,
    @field:Json(name = "full") val full: String,
)