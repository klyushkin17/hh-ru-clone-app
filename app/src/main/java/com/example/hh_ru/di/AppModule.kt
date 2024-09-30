package com.example.hh_ru.di

import android.app.Application
import androidx.room.Room
import com.example.hh_ru.data.local.dao.FavoriteVacanciesDatabase
import com.example.hh_ru.data.remote.HhRuApi
import com.example.hh_ru.data.repository.HhRuRepositoryImpl
import com.example.hh_ru.domain.repository.HhRuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFavoriteVacanciesDatabase(app: Application): FavoriteVacanciesDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteVacanciesDatabase::class.java,
            "favoriteVacancies.db"
        ).createFromAsset("databases/favorite_vacancies.db").build()
    }

    @Provides
    @Singleton
    fun provideHhRuApi(): HhRuApi {
        return Retrofit.Builder()
            .baseUrl(HhRuApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}