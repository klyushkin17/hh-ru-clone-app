package com.example.hh_ru.data.mapper

import com.example.hh_ru.data.remote.dto.OfferDto
import com.example.hh_ru.data.remote.dto.OfferListDto
import com.example.hh_ru.domain.model.Offer
import com.example.hh_ru.domain.model.OfferList

fun OfferDto.toOffer(): Offer {
    return Offer(
        offerId = offerId,
        offerTitle = offerTitle,
        buttonText = buttonInfo?.buttonText,
        link = link
    )
}

fun List<OfferDto>.toListOfOffer(): List<Offer> {
    return this.map {
        Offer(
            offerId = it.offerId,
            offerTitle = it.offerTitle,
            buttonText = it.buttonInfo?.buttonText,
            link = it.link
        )
    }
}

fun OfferListDto.toOfferList(): OfferList {
    return OfferList(
        offerList = offerList.toListOfOffer()
    )
}