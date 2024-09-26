package com.example.hh_ru.di

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
    fun provideHhRuApi(): HhRuApi {
        return Retrofit.Builder()
            .baseUrl(HhRuApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideHhRuRepository(): HhRuRepository {
        return HhRuRepositoryImpl(api = provideHhRuApi())
    }
}