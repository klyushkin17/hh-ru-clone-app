package com.example.hh_ru.domain.model

import androidx.room.PrimaryKey

data class FavoriteVacancy(
    val vacancyId: String,
    val lookingNumber: Int?,
    val vacancyTitle: String,
    val town: String,
    val company: String,
    val previewText: String,
)
