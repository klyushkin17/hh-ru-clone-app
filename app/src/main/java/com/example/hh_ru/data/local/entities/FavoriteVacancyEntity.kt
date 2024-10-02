package com.example.hh_ru.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.hh_ru.data.remote.dto.AddressInfoDto

@Entity
data class FavoriteVacancyEntity(
    @PrimaryKey(autoGenerate = false) val vacancyId: String,
    val lookingNumber: Int?,
    val vacancyTitle: String,
    val town: String,
    val company: String,
    val previewText: String,
    val publishedDate: String,
)
