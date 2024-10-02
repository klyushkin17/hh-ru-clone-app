package com.example.hh_ru.presentation.main_screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.unpackInt1
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hh_ru.R
import com.example.hh_ru.ui.theme.findIconColor
import com.example.hh_ru.ui.theme.moreButtonColor
import com.example.hh_ru.ui.theme.placeholderTextColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.topBarColor
import com.example.hh_ru.ui.theme.whiteIconColor
import com.example.hh_ru.ui.theme.whiteTextColor
import com.example.hh_ru.utils.UiEvent

@Composable
fun MainScreenRoot(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(MainScreenEvent.OnTriggerGettingFavoritesFromLaunchEffect)
    }

    MainScreen(
        viewModel = viewModel,
        state = state,
    )
}

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    state: MainScreenState,
) {
    val rootColumnScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rootColumnScrollState)
        ,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TopBar(
                state = state,
                onEvent = viewModel::onEvent,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (state.offerList.offerList.isNotEmpty()) {
            OffersScrollableList(state = state)
            Spacer(modifier = Modifier.height(32.dp))
        }
        VacanciesList(
            viewModel = viewModel,
            state = state
        )
        Spacer(modifier = Modifier.height(24.dp))
        MoreButton(
            viewModel = viewModel,
            state = state,
        )
    }
}

@Composable
fun OffersScrollableList(
    state: MainScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
            items(state.offerList.offerList) { offerElement ->
                Spacer(modifier = Modifier.width(8.dp))
                OffersListElement(offerElement)
                Spacer(modifier = Modifier.width(8.dp))
            }
            items(1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun VacanciesList(
    viewModel: MainScreenViewModel,
    state: MainScreenState
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.vacancies_for_you_title),
            color = whiteTextColor,
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (state.vacancyList.vacancyList.isNotEmpty()) {
            for (vacancyIndex in 0..2) {
                VacanciesListElement(
                    viewModel = viewModel,
                    vacancy = state.vacancyList.vacancyList[vacancyIndex],
                    state = state
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MoreButton(
    viewModel: MainScreenViewModel,
    state: MainScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Button(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = moreButtonColor
            ),
            onClick = {
                viewModel.onEvent(MainScreenEvent.OnMoreButtonClick)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.more) + " " +
                            state.vacancyList.vacancyList.size.toString() + " " +
                            viewModel.getVacancyDeclinationByNumber(state.vacancyList.vacancyList.size.toString()),
                    color = whiteTextColor,
                    fontFamily = sanFrancisco,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.8.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit,
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
                    onEvent(MainScreenEvent.OnSearchFieldValueChange(newSearchValue))
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
                            text = stringResource(id = R.string.search_bar_placeholder),
                            fontFamily = sanFrancisco,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = placeholderTextColor
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.find_icon),
                        contentDescription = "find_icon",
                        tint = findIconColor
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
                tint = whiteIconColor
            )
        }
    }
}

