package com.example.hh_ru

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
import com.example.hh_ru.presentation.main_screen.MainScreenRoot
import com.example.hh_ru.presentation.suitable_vacancies_screen.SuitableVacanciesScreenRoot
import com.example.hh_ru.ui.theme.HhruTheme
import com.example.hh_ru.ui.theme.backgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = backgroundColor
            ) {
                HhruTheme {
                    SuitableVacanciesScreenRoot()
                }
            }
        }
    }
}
