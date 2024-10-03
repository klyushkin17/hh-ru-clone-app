package com.example.hh_ru.presentation.bottom_navigation_bar

import com.example.hh_ru.R
import com.example.hh_ru.utils.Routes

data class BottomNavBarItem(
    val title: Int,
    val icon: Int,
    val route: String,
    val selectedIcon: Int,
    val badgeCount: Int? = null
)