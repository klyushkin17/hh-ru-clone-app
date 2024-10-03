package com.example.hh_ru.presentation.bottom_navigation_bar

import android.media.Image
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hh_ru.R
import com.example.hh_ru.presentation.favorite_vacancies_screen.FavoriteVacanciesScreenViewModel
import com.example.hh_ru.ui.theme.backgroundColor
import com.example.hh_ru.ui.theme.bridgeContainerColor
import com.example.hh_ru.ui.theme.grayIconColor
import com.example.hh_ru.ui.theme.grayTextColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.selectedIconColor
import com.example.hh_ru.ui.theme.selectedTextColor
import com.example.hh_ru.ui.theme.unselectedIconColor
import com.example.hh_ru.ui.theme.unselectedTextColor
import com.example.hh_ru.ui.theme.whiteTextColor
import com.example.hh_ru.utils.Resource
import com.example.hh_ru.utils.Routes

@Composable
fun BottomNavBar(
    navController: NavController,
    viewModel: BottomNavBarViewModel = hiltViewModel()
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    viewModel.onBadgeCountChange()

    NavigationBar(
        containerColor = backgroundColor
    ) {
        viewModel.items.forEachIndexed { index, bottomNavBarItem ->
            NavigationBarItem(
                selected = viewModel.checkCurrentDestination(currentDestination?.route) == index,
                onClick = {
                    viewModel.onBadgeCountChange()
                    navController.navigate(bottomNavBarItem.route)
                },
                label = {
                    Text(
                        text = stringResource(id = bottomNavBarItem.title),
                        fontFamily = sanFrancisco,
                        fontWeight = FontWeight.Normal,
                        color = if(index == viewModel.checkCurrentDestination(currentDestination?.route)) {
                            selectedTextColor
                        } else unselectedIconColor,
                        fontSize = 10.sp
                    )
                },
                icon = {
                    BadgedBox(badge = {
                        if(bottomNavBarItem.badgeCount != null && bottomNavBarItem.badgeCount != 0) {
                            Badge(
                                containerColor = bridgeContainerColor,
                                contentColor = whiteTextColor
                            ) {
                                Text(text = bottomNavBarItem.badgeCount.toString())
                            }
                        }
                    } ) {
                        Icon(
                            painter = painterResource(id = if(index == viewModel.checkCurrentDestination(currentDestination?.route)) {
                                bottomNavBarItem.selectedIcon
                            } else bottomNavBarItem.icon),
                            contentDescription = stringResource(id = bottomNavBarItem.title),
                            tint = if(index == viewModel.checkCurrentDestination(currentDestination?.route)) {
                                selectedIconColor
                            } else unselectedIconColor
                        )
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = Color.Transparent,
                    selectedTextColor = selectedTextColor,
                    selectedIconColor = selectedIconColor,
                    unselectedTextColor = unselectedTextColor,
                    unselectedIconColor = unselectedIconColor,
                    disabledTextColor = grayTextColor,
                    disabledIconColor = grayIconColor
                )
            )
        }
    }
}
