package com.example.hh_ru.presentation.suitable_vacancies_screen

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hh_ru.R
import com.example.hh_ru.presentation.main_screen.MainScreenState
import com.example.hh_ru.presentation.main_screen.MainScreenViewModel
import com.example.hh_ru.ui.theme.backgroundColor
import com.example.hh_ru.ui.theme.findIconColor
import com.example.hh_ru.ui.theme.placeholderTextColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.scaffoldBackgroundColor
import com.example.hh_ru.ui.theme.sortIconColor
import com.example.hh_ru.ui.theme.sortTextColor
import com.example.hh_ru.ui.theme.topBarColor
import com.example.hh_ru.ui.theme.whiteIconColor
import com.example.hh_ru.ui.theme.whiteTextColor
import com.example.hh_ru.utils.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SuitableVacanciesScreenRoot(
    viewModel: SuitableVacanciesScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
){
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }

    Scaffold(
        containerColor = backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .width(99.dp)
                    .height(48.dp),
                onClick = { /*TODO*/ },
                containerColor = scaffoldBackgroundColor,
                shape = RoundedCornerShape(50.dp)
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.map_icon),
                        tint = whiteIconColor,
                        contentDescription = "map_icon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.map_button_text),
                        fontFamily = sanFrancisco,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = whiteTextColor,
                        lineHeight = 19.2.sp,
                    )
                }
            }
        }
    ){
        SuitableVacanciesScreen(
            state = state,
            viewModel = viewModel
        )
    }
}

@Composable
fun SuitableVacanciesScreen(
    state: SuitableVacanciesScreenState,
    viewModel: SuitableVacanciesScreenViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        TopBar(
            state = state,
            onEvent = viewModel::onEvent,
        )
        Spacer(modifier = Modifier.height(16.dp))
        SortingSection(
            state = state,
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(14.dp))
        VacanciesList(
            viewModel = viewModel,
            state = state
        )
    }

}

@Composable
fun VacanciesList(
    viewModel: SuitableVacanciesScreenViewModel,
    state: SuitableVacanciesScreenState
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(state.vacancyList.vacancyList) { vacancy ->
            VacanciesListElement(
                viewModel = viewModel,
                vacancy = vacancy
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    state: SuitableVacanciesScreenState,
    onEvent: (SuitableVacanciesScreenEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.1f),
            verticalAlignment = Alignment.Top
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                query = state.searchFiledValue,
                onQueryChange = { newSearchValue ->
                    onEvent(SuitableVacanciesScreenEvent.OnSearchFieldValueChange(newSearchValue))
                },
                onSearch = {},
                active = false,
                onActiveChange = {},
                placeholder = {
                    Row(modifier = Modifier
                        .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.suitable_screen_search_bar_placeholder),
                            fontFamily = sanFrancisco,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = placeholderTextColor
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onEvent(SuitableVacanciesScreenEvent.OnBackArrowIconClick)
                            },
                        painter = painterResource(id = R.drawable.back_arrow_icon),
                        contentDescription = "find_icon",
                        tint = whiteIconColor
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = SearchBarDefaults.colors(
                    containerColor = topBarColor,
                ),
                content = {}
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier
            .size(55.dp)
            .background(color = topBarColor, shape = RoundedCornerShape(8.dp))
            .padding(18.dp)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.filter_icon),
                contentDescription = "filter_icon",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SortingSection(
    state: SuitableVacanciesScreenState,
    viewModel: SuitableVacanciesScreenViewModel
) {
    Row(
        modifier = Modifier
            .height(17.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = state.vacancyList.vacancyList.size.toString() + " " +
                    viewModel.getVacancyDeclinationByNumber(state.vacancyList.vacancyList.size.toString()),
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = whiteTextColor,
            lineHeight = 16.8.sp,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.sort_text),
                fontFamily = sanFrancisco,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = sortTextColor,
                lineHeight = 16.8.sp,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                modifier = Modifier
                    .height(16.dp),
                painter = painterResource(id = R.drawable.sort_icon),
                tint = sortIconColor,
                contentDescription = "sort_icon"
            )
        }
    }
}
