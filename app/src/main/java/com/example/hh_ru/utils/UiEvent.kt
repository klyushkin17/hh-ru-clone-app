package com.example.hh_ru.utils

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    object  PopBackStack: UiEvent()
}