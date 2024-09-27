package com.example.hh_ru.di

import com.example.hh_ru.data.repository.HhRuRepositoryImpl
import com.example.hh_ru.domain.repository.HhRuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHhRuRepository(
        hhRuRepositoryImpl: HhRuRepositoryImpl
    ): HhRuRepository
}