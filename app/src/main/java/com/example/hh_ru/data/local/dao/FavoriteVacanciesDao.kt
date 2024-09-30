package com.example.hh_ru.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hh_ru.data.local.entities.FavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteVacanciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVacancyToFavorites(vacancy: FavoriteVacancyEntity)

    @Query("DELETE FROM FavoriteVacancyEntity WHERE vacancyId = :vacancyId")
    fun deleteVacancyFromFavorites(vacancyId: String)

    @Query("SELECT * FROM FavoriteVacancyEntity")
    fun getFavoriteVacancies(): List<FavoriteVacancyEntity>
}