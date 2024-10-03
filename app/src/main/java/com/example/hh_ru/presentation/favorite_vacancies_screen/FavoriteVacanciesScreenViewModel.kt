package com.example.hh_ru.presentation.favorite_vacancies_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hh_ru.data.mapper.toListOfFavoriteVacancy
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.utils.Routes
import com.example.hh_ru.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FavoriteVacanciesScreenViewModel @Inject constructor(
    private val hhRuRepository: HhRuRepository
): ViewModel() {

    private val _state = MutableStateFlow(FavoriteVacanciesScreenState())
    val state = _state.asStateFlow()

    init {
        getFavoriteVacancies()
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FavoriteVacanciesScreenEvent) {
        when(event) {
            is FavoriteVacanciesScreenEvent.OnLikeIconClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    hhRuRepository
                        .deleteVacancyFromFavorites(vacancyId = event.vacancy.vacancyId)
                    getFavoriteVacancies()
                }
            }
            is FavoriteVacanciesScreenEvent.OnCloseComposition -> {
                viewModelScope.launch(Dispatchers.IO) {
                    state.value.favoriteVacanciesIdsMap.forEach { record ->
                        if (!record.value) hhRuRepository.deleteVacancyFromFavorites(vacancyId = record.key)
                    }
                }
            }
            is FavoriteVacanciesScreenEvent.OnVacancyClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.VACANCY_SCREEN))
            }
        }
    }

    private fun getFavoriteVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            hhRuRepository
                .getFavoriteVacancies()
                .collect { result ->
                    _state.value = state.value.copy(
                        favoriteVacanciesList = result.toListOfFavoriteVacancy(),
                        favoriteVacanciesIdsMap = result.associateBy(
                            {it.vacancyId},
                            {true}
                        )
                    )
                }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}