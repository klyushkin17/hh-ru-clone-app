package com.example.hh_ru.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val hhRuRepository: HhRuRepository,
): ViewModel() {

    init {
        getVacanciesAndOffers()
    }

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.OnVacancyClick -> {

            }
        }
    }

    private fun getVacanciesAndOffers() {
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
                        is Resource.Loading -> Unit
                        is Resource.Error -> Unit
                    }
                }
        }
    }
}