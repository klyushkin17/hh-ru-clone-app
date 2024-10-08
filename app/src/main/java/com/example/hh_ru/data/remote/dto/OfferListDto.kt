package com.example.hh_ru.data.remote.dto

import com.squareup.moshi.Json


data class OfferListDto(
    @field:Json(name = "offers") val offerList: List<OfferDto>,
)