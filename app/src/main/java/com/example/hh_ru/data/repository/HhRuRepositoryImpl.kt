package com.example.hh_ru.data.repository

import com.example.hh_ru.data.local.dao.FavoriteVacanciesDatabase
import com.example.hh_ru.data.local.entities.FavoriteVacancyEntity
import com.example.hh_ru.data.mapper.toOfferList
import com.example.hh_ru.data.mapper.toVacancy
import com.example.hh_ru.data.mapper.toVacancyList
import com.example.hh_ru.data.remote.HhRuApi
import com.example.hh_ru.domain.model.OfferList
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.domain.model.VacancyList
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HhRuRepositoryImpl @Inject constructor(
    private val api: HhRuApi,
    private val db: FavoriteVacanciesDatabase,
): HhRuRepository {

    private val dao = db.favoriteVacanciesDao

    override suspend fun getVacancies(): Flow<Resource<VacancyList>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = api.getVacancies()
                emit(Resource.Success(data = result.toVacancyList()))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "IOException: Couldn't load the data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "HttpException: Couldn't load the data"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getOffers(): Flow<Resource<OfferList>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = api.getOffers()
                emit(Resource.Success(data = result.toOfferList()))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "IOException: Couldn't load the data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "HttpException: Couldn't load the data"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun insertVacancyToFavorite(vacancy: FavoriteVacancyEntity) {
        dao.insertVacancyToFavorites(vacancy)
    }

    override suspend fun deleteVacancyFromFavorites(vacancyId: String) {
        dao.deleteVacancyFromFavorites(vacancyId)
    }

    override suspend fun getFavoriteVacancies(): Flow<List<FavoriteVacancyEntity>> {
        return flow {
            emit(dao.getFavoriteVacancies())
        }
    }
}