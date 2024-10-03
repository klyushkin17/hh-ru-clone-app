package com.example.hh_ru.presentation.suitable_vacancies_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hh_ru.data.mapper.toFavoriteVacancyEntity
import com.example.hh_ru.data.mapper.toListOfFavoriteVacancy
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.presentation.main_screen.MainScreenEvent
import com.example.hh_ru.utils.Resource
import com.example.hh_ru.utils.Routes
import com.example.hh_ru.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.internal.connection.RouteException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class SuitableVacanciesScreenViewModel @Inject constructor(
    private val hhRuRepository: HhRuRepository
): ViewModel() {

    private val _state = MutableStateFlow(SuitableVacanciesScreenState())
    val state = _state.asStateFlow()

    init {
        getVacancies()
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SuitableVacanciesScreenEvent) {
        when(event) {
            is SuitableVacanciesScreenEvent.OnVacancyClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.VACANCY_SCREEN))
            }
            is SuitableVacanciesScreenEvent.OnSearchFieldValueChange -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        searchFiledValue = event.newSearchFieldValue
                    )
                }
            }
            is SuitableVacanciesScreenEvent.OnBackArrowIconClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.MAIN_SCREEN))
            }

            is SuitableVacanciesScreenEvent.OnLikeIconClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when(event.isFavorite){
                        true -> {
                            hhRuRepository.insertVacancyToFavorite(vacancy = event.vacancy.toFavoriteVacancyEntity())
                            getFavoriteVacancies()
                        }
                        false -> {
                            hhRuRepository.deleteVacancyFromFavorites(vacancyId = event.vacancy.vacancyId)
                            getFavoriteVacancies()
                        }
                    }
                }
            }
            is SuitableVacanciesScreenEvent.OnTriggerGettingFavoritesFromLaunchEffect -> {
                getFavoriteVacancies()
            }
            is SuitableVacanciesScreenEvent.GoToFavoritesTEMP -> {
                sendUiEvent(UiEvent.Navigate(Routes.FAVORITE_VACANCIES_SCREEN))
            }
        }
    }

    private fun getFavoriteVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            hhRuRepository
                .getFavoriteVacancies()
                .collect{ result ->
                    _state.value = state.value.copy(
                        favoriteVacancyIds = result.toListOfFavoriteVacancy().map { it.vacancyId }.toList()
                    )
                }
        }
    }

    private fun getVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            hhRuRepository
                .getVacancies()
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { vacancies ->
                                _state.value = state.value.copy(
                                    vacancyList = vacancies
                                )
                            }
                        }
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                    }
                }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}