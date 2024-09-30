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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                        containerColor = backgroundColor
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.MAIN_SCREEN
                        ) {
                            composable(route = Routes.MAIN_SCREEN) {
                                MainScreenRoot(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }

                            composable(route = Routes.SUITABLE_VACANCIES_SCREEN) {
                                SuitableVacanciesScreenRoot(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    },
                                    onPopBackStack = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
