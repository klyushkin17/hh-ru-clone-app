package com.example.hh_ru.presentation.main_screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.example.hh_ru.domain.model.Offer
import com.example.hh_ru.presentation.tools.shimmerEffect
import com.example.hh_ru.ui.theme.greenTextColor
import com.example.hh_ru.ui.theme.mainElementBackgroundColor
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hh_ru.R
import com.example.hh_ru.presentation.bottom_navigation_bar.BottomNavBarViewModel
import com.example.hh_ru.ui.theme.findIconColor
import com.example.hh_ru.ui.theme.moreButtonColor
import com.example.hh_ru.ui.theme.placeholderTextColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.topBarColor
import com.example.hh_ru.ui.theme.whiteIconColor
import com.example.hh_ru.ui.theme.whiteTextColor
import com.example.hh_ru.utils.UiEvent


@Composable
fun ShimmerLoadingMainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        OffersScrollableList()
        Spacer(modifier = Modifier.height(32.dp))
        VacanciesList()
    }
}

@Composable
fun OffersScrollableList() {
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
            items(3) {
                Spacer(modifier = Modifier.width(8.dp))
                OffersListElement()
                Spacer(modifier = Modifier.width(8.dp))
            }
            items(1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun VacanciesList(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(modifier = Modifier
            .height(24.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        repeat(2) {
            VacanciesListElement()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun OffersListElement(){
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxHeight()
            .width(132.dp)
            .background(color = mainElementBackgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(top = 10.dp)
            .padding(horizontal = 8.dp)
    ) {
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .height(33.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .shimmerEffect()
        )
    }
}


@Composable
fun VacanciesListElement() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = mainElementBackgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.9f)
            ) {
                Box(modifier = Modifier
                    .height(16.8.dp)
                    .width(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier
                    .height(19.2.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier
                    .height(33.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .height(16.8.dp)
                    .width(140.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier
                    .height(16.8.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
                )
            }
        }
        Spacer(modifier = Modifier.height(21.dp))
        Box(modifier = Modifier
            .height(32.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .shimmerEffect()
        )
    }
}




