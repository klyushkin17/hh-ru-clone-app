package com.example.hh_ru.domain.model

data class Vacancy(
    val vacancyId: String,
    val lookingNumber: Int?,
    val vacancyTitle: String,
    val town: String,
    val street: String,
    val house: String,
    val company: String,
    val previewText: String,
    val text: String,
    val publishedDate: String,
    val isFavorites: Boolean,
    val short: String?,
    val full: String,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val question: List<String>?,
)
