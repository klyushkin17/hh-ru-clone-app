package com.example.hh_ru.presentation.main_screen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hh_ru.R
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.ui.theme.filledLikeIconColor
import com.example.hh_ru.ui.theme.grayIconColor
import com.example.hh_ru.ui.theme.grayTextColor
import com.example.hh_ru.ui.theme.greenTextColor
import com.example.hh_ru.ui.theme.mainElementBackgroundColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.vacancyButtonColor
import com.example.hh_ru.ui.theme.whiteTextColor

@Composable
fun VacanciesListElement(
    viewModel: MainScreenViewModel,
    vacancy: Vacancy,
    state: MainScreenState
) {
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
                vacancy.lookingNumber?.let {
                    Text(
                        text = stringResource(id = R.string.now_watching) + " " +
                                viewModel.getWatchDeclinationByNumber(vacancy.lookingNumber.toString()) + " " +
                                vacancy.lookingNumber.toString() + " " +
                                viewModel.getPeopleDeclinationByNumber(vacancy.lookingNumber.toString()),
                        color = greenTextColor,
                        fontFamily = sanFrancisco,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.8.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Text(
                    text = vacancy.vacancyTitle,
                    color = whiteTextColor,
                    fontFamily = sanFrancisco,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 19.2.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = vacancy.town,
                    color = whiteTextColor,
                    fontFamily = sanFrancisco,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.8.sp
                )
                Row(
                    modifier = Modifier
                        .height(17.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = vacancy.company,
                        color = whiteTextColor,
                        fontFamily = sanFrancisco,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.8.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier
                            .height(16.dp),
                        painter = painterResource(id = R.drawable.checked_icon),
                        contentDescription = "checked_icon",
                        tint = grayIconColor
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .height(17.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        modifier = Modifier
                            .height(16.dp),
                        painter = painterResource(id = R.drawable.bag_icon),
                        contentDescription = "bag_icon",
                        tint = whiteTextColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = vacancy.previewText,
                        color = whiteTextColor,
                        fontFamily = sanFrancisco,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.8.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        stringResource(id = R.string.posted) + " " + viewModel.formatDateString(vacancy.publishedDate)
                    } else {
                        stringResource(id = R.string.posted) + " " + viewModel.formatDateStringLowVersion(vacancy.publishedDate)
                    },
                    color = grayTextColor,
                    fontFamily = sanFrancisco,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.8.sp
                )
            }
            if (
                state.favoriteVacancyIds.contains(vacancy.vacancyId)
            ) {
                Icon(
                    modifier = Modifier
                        .height(24.dp)
                        .clickable {
                            viewModel.onEvent(MainScreenEvent.OnLikeIconClick(vacancy, isFavorite = false))
                        },
                    painter = painterResource(id = R.drawable.filled_like_icon),
                    contentDescription = "filled_like_icon",
                    tint = filledLikeIconColor,
                )
            } else {
                Icon(
                    modifier = Modifier
                        .height(24.dp)
                        .clickable {
                            viewModel.onEvent(MainScreenEvent.OnLikeIconClick(vacancy, isFavorite = true))
                        },
                    painter = painterResource(id = R.drawable.empty_liked_icon),
                    contentDescription = "empty_like_icon",
                    tint = grayIconColor,
                )
            }
        }
        Spacer(modifier = Modifier.height(21.dp))
        Button(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = vacancyButtonColor
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.vacancy_button_text),
                    color = whiteTextColor,
                    fontFamily = sanFrancisco,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.2.sp
                )
            }
        }
    }
}