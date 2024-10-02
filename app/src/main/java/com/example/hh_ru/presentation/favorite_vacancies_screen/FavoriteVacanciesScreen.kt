package com.example.hh_ru.presentation.favorite_vacancies_screen

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hh_ru.R
import com.example.hh_ru.domain.model.FavoriteVacancy
import com.example.hh_ru.presentation.suitable_vacancies_screen.VacanciesList
import com.example.hh_ru.ui.theme.grayTextColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.whiteTextColor
import com.example.hh_ru.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

@Composable
fun FavoriteVacanciesScreenRoot(
    viewModel: FavoriteVacanciesScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onNavigateUp: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }

    DisposableEffect(key1 = viewModel) {
        onDispose {
            viewModel.onEvent(FavoriteVacanciesScreenEvent.OnCloseComposition)
        }
    }

    FavoriteVacanciesScreen(
        state = state,
        viewModel = viewModel,
    )
}

@Composable
fun FavoriteVacanciesScreen(
    state: FavoriteVacanciesScreenState,
    viewModel: FavoriteVacanciesScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)

    ) {
        Text(
            text = stringResource(id = R.string.favorites_title),
            color = whiteTextColor,
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = state.favoriteVacanciesList.size.toString() + " " +
                    viewModel.getVacancyDeclinationByNumber(state.favoriteVacanciesList.size.toString()),
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = grayTextColor,
            lineHeight = 16.8.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FavoriteVacanciesList(
            state = state,
            viewModel = viewModel
        )
    }
}

@Composable
fun FavoriteVacanciesList(
    state: FavoriteVacanciesScreenState,
    viewModel: FavoriteVacanciesScreenViewModel
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(state.favoriteVacanciesList) { vacancy ->
            FavoriteVacanciesListElement(
                state = state,
                viewModel = viewModel,
                vacancy = vacancy
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}