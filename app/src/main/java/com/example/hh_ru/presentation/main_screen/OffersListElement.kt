package com.example.hh_ru.presentation.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hh_ru.R
import com.example.hh_ru.domain.model.Offer
import com.example.hh_ru.ui.theme.greenTextColor
import com.example.hh_ru.ui.theme.mainElementBackgroundColor
import com.example.hh_ru.ui.theme.sanFrancisco
import com.example.hh_ru.ui.theme.topBarColor
import com.example.hh_ru.ui.theme.whiteTextColor

@Composable
fun OffersListElement(
    offer: Offer
){
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
        ){
            offer.offerId?.let {
                Image(
                    painter = when(offer.offerId) {
                        stringResource(id = R.string.level_up_id) -> painterResource(id = R.drawable.level_up_resume_image)
                        stringResource(id = R.string.near_vacancies_id) -> painterResource(id = R.drawable.near_vacancies_image)
                        stringResource(id = R.string.temporary_job_id) -> painterResource(id = R.drawable.temporary_job_image)
                        else -> painterResource(id = R.drawable.near_vacancies_image)
                    },
                    contentDescription = "offer_image"
                )
            }
        }     
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = offer.offerTitle,
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            maxLines = if(offer.buttonText != null) 2 else 3,
            lineHeight = 16.8.sp,
            color = whiteTextColor,
            overflow = TextOverflow.Clip
        )
        offer.buttonText?.let {
            Text(
                text = offer.buttonText,
                fontFamily = sanFrancisco,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 16.8.sp,
                color = greenTextColor
            )
        }
    }
}