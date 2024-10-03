package com.example.hh_ru.presentation.dummy_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hh_ru.R
import com.example.hh_ru.ui.theme.backButtonColor
import com.example.hh_ru.ui.theme.backgroundColor
import com.example.hh_ru.ui.theme.grayIconColor
import com.example.hh_ru.ui.theme.grayTextColor
import com.example.hh_ru.ui.theme.mainElementBackgroundColor
import com.example.hh_ru.ui.theme.moreButtonColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.scaffoldBackgroundColor
import com.example.hh_ru.ui.theme.whiteIconColor
import com.example.hh_ru.ui.theme.whiteTextColor
import com.example.hh_ru.utils.Routes

@Composable
fun DummyScreenRoot(
    navController: NavController
) {
    DummyScreen(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DummyScreen(
    navController: NavController
) {
    Scaffold(
        containerColor = backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 80.dp),
                onClick = {
                    navController.navigate(Routes.MAIN_SCREEN)
                },
                containerColor = backButtonColor,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.back_text),
                        color = whiteTextColor,
                        fontFamily = sanFrancisco,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.8.sp
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .height(60.dp),
                painter = painterResource(id = R.drawable.work_icon),
                contentDescription = "work_icon",
                tint = grayIconColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.apologize_text),
                fontFamily = sanFrancisco,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = grayTextColor
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}