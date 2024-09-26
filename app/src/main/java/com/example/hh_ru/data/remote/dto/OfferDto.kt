package com.example.hh_ru.data.remote.dto

import com.squareup.moshi.Json

data class OfferDto(
    @field:Json(name = "id") val offerId: String?,
    @field:Json(name = "title") val offerTitle: String,
    @field:Json(name = "button") val buttonInfo: ButtonInfoDto?,
    @field:Json(name = "link") val link: String,
)

data class ButtonInfoDto(
    @field:Json(name = "text") val buttonText: String?,
)