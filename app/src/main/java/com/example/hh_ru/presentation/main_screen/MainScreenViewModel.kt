package com.example.hh_ru.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hh_ru.data.mapper.toFavoriteVacancyEntity
import com.example.hh_ru.data.mapper.toListOfFavoriteVacancy
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.presentation.bottom_navigation_bar.BottomNavBarViewModel
import com.example.hh_ru.utils.Resource
import com.example.hh_ru.utils.Routes
import com.example.hh_ru.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val hhRuRepository: HhRuRepository,
): ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        getVacanciesAndOffers()
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.OnVacancyClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.VACANCY_SCREEN))
            }
            is MainScreenEvent.OnSearchFieldValueChange -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        searchFiledValue = event.newSearchFieldValue
                    )
                }
            }
            is MainScreenEvent.OnMoreButtonClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.SUITABLE_VACANCIES_SCREEN))
            }
            is MainScreenEvent.OnLikeIconClick -> {
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
            is MainScreenEvent.OnTriggerGettingFavoritesFromLaunchEffect -> {
                getFavoriteVacancies()
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

    private fun getVacanciesAndOffers() {
        viewModelScope.launch {
            hhRuRepository
                .getOffers()
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { offers ->
                                _state.value = state.value.copy(
                                    offerList = offers
                                )
                            }
                        }
                        is Resource.Loading -> Unit
                        is Resource.Error -> Unit
                    }
                }
        }
        viewModelScope.launch {
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
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                screenIsLoading = result.isLoading
                            )
                        }
                        is Resource.Error -> Unit
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