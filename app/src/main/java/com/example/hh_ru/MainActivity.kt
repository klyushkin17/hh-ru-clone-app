package com.example.hh_ru

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.hh_ru.presentation.bottom_navigation_bar.BottomNavBar
import com.example.hh_ru.presentation.bottom_navigation_bar.BottomNavBarItem
import com.example.hh_ru.presentation.bottom_navigation_bar.BottomNavBarViewModel
import com.example.hh_ru.presentation.dummy_screen.DummyScreen
import com.example.hh_ru.presentation.dummy_screen.DummyScreenRoot
import com.example.hh_ru.presentation.favorite_vacancies_screen.FavoriteVacanciesScreenRoot
import com.example.hh_ru.presentation.main_screen.MainScreenRoot
import com.example.hh_ru.presentation.suitable_vacancies_screen.SuitableVacanciesScreenRoot
import com.example.hh_ru.ui.theme.HhruTheme
import com.example.hh_ru.ui.theme.backgroundColor
import com.example.hh_ru.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HhruTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(navController = navController)
                        },
                        containerColor = backgroundColor
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "main_graph"
                        ) {

                            navigation(
                                startDestination = Routes.MAIN_SCREEN,
                                route = "main_graph"
                            ) {
                                composable(route = Routes.MAIN_SCREEN) {

                                    MainScreenRoot(
                                        onNavigate = {
                                            navController.navigate(it.route)
                                        },
                                    )
                                }

                                composable(route = Routes.SUITABLE_VACANCIES_SCREEN) {
                                    SuitableVacanciesScreenRoot(
                                        onNavigate = {
                                            navController.navigate(it.route)
                                        },
                                        onNavigateUp = {
                                            navController.navigateUp()
                                        }
                                    )
                                }
                                composable(route = Routes.FAVORITE_VACANCIES_SCREEN) {
                                    FavoriteVacanciesScreenRoot(
                                        onNavigate = {
                                            navController.navigate(it.route)
                                        },
                                        onNavigateUp = {
                                            navController.navigateUp()
                                        }
                                    )
                                }
                                composable(route = Routes.MESSAGE_SCREEN) {
                                    DummyScreenRoot(navController = navController)
                                }
                                composable(route = Routes.PROFILE_SCREEN) {
                                    DummyScreenRoot(navController = navController)
                                }
                                composable(route = Routes.ANSWERS_SCREEN) {
                                    DummyScreenRoot(navController = navController)
                                }
                                composable(route = Routes.VACANCY_SCREEN) {
                                    DummyScreenRoot(navController = navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
