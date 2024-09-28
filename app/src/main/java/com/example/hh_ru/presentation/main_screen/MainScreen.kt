package com.example.hh_ru.presentation.main_screen

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hh_ru.R
import com.example.hh_ru.ui.theme.backgroundColor
import com.example.hh_ru.ui.theme.findIconColor
import com.example.hh_ru.ui.theme.placeholderTextColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.topBarColor

@Composable
fun MainScreenRoot(
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    MainScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TopBar(
                state = state,
                onEvent = onEvent
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
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
                query = "",
                onQueryChange = {

                },
                onSearch = {
                    TODO()
                },
                active = false,
                onActiveChange = {
                    TODO()
                },
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
                content = {}
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier
            .size(55.dp)
            .background(color = topBarColor, shape = RoundedCornerShape(8.dp))
            .padding(15.dp)
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


