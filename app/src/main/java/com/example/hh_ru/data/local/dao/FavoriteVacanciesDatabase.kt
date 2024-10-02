package com.example.hh_ru.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hh_ru.data.local.entities.FavoriteVacancyEntity

@Database(
    entities = [
        FavoriteVacancyEntity::class,
    ],
    version = 4
)
abstract class FavoriteVacanciesDatabase: RoomDatabase() {

    abstract val favoriteVacanciesDao: FavoriteVacanciesDao
}