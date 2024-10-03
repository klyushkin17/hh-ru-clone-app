package com.example.hh_ru.presentation.bottom_navigation_bar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hh_ru.R
import com.example.hh_ru.domain.model.FavoriteVacancy
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.domain.repository.HhRuRepository
import com.example.hh_ru.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
@SuppressLint("MutableCollectionMutableState")
class BottomNavBarViewModel @Inject constructor(
    private val hhRuRepository: HhRuRepository
): ViewModel() {

    var favoriteVacancies by mutableIntStateOf(0)
        private set

    var items by mutableStateOf(
        listOf(
            BottomNavBarItem(
                title = R.string.search_title,
                icon = R.drawable.find_icon,
                route = Routes.MAIN_SCREEN,
                selectedIcon = R.drawable.find_icon
            ),
            BottomNavBarItem(
                title = R.string.favorites_title,
                icon = R.drawable.empty_liked_icon,
                selectedIcon = R.drawable.filled_like_icon,
                route = Routes.FAVORITE_VACANCIES_SCREEN,
                badgeCount = favoriteVacancies
            ),
            BottomNavBarItem(
                title = R.string.answers_title,
                icon = R.drawable.letter_icon,
                route = Routes.ANSWERS_SCREEN,
                selectedIcon = R.drawable.letter_icon
            ),
            BottomNavBarItem(
                title = R.string.messages_title,
                icon = R.drawable.message_icon,
                route = Routes.MESSAGE_SCREEN,
                selectedIcon = R.drawable.message_icon
            ),
            BottomNavBarItem(
                title = R.string.profile_title,
                icon = R.drawable.profile_icon,
                route = Routes.PROFILE_SCREEN,
                selectedIcon = R.drawable.profile_icon
            ),
        )
    )
        private set

    init {
        val job = viewModelScope.launch(Dispatchers.IO) {
            hhRuRepository
                .getFavoriteVacancies()
                .collect{ result ->
                    favoriteVacancies = result.size
                }

        }
        viewModelScope.launch {
            job.join()
            val newItemsList = items.toMutableList()
            newItemsList[1] = newItemsList[1].copy(
                badgeCount = favoriteVacancies
            )
            items = newItemsList.toList()
        }

    }

    fun onBottomNavBarItemClick(index: Int) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            hhRuRepository
                .getFavoriteVacancies()
                .collect{ result ->
                    favoriteVacancies = result.size
                }
        }
        viewModelScope.launch {
            job.join()
            val newItemsList = items.toMutableList()
            newItemsList[1] = newItemsList[1].copy(
                badgeCount = favoriteVacancies
            )
            items = newItemsList.toList()
        }
    }

    fun checkCurrentDestination(currentScreen: String?): Int{
        return when (currentScreen){
            Routes.SUITABLE_VACANCIES_SCREEN -> 0
            Routes.MAIN_SCREEN -> 0
            Routes.FAVORITE_VACANCIES_SCREEN -> 1
            Routes.ANSWERS_SCREEN -> 2
            Routes.MESSAGE_SCREEN -> 3
            Routes.PROFILE_SCREEN -> 4
            else -> 0
        }
    }
}