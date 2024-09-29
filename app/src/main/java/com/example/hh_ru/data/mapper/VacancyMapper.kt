package com.example.hh_ru.data.mapper

import com.example.hh_ru.data.remote.dto.VacancyDto
import com.example.hh_ru.data.remote.dto.VacancyListDto
import com.example.hh_ru.domain.model.Vacancy
import com.example.hh_ru.domain.model.VacancyList
import kotlin.math.exp

fun VacancyDto.toVacancy(): Vacancy {
    return Vacancy(
        vacancyId = vacancyId,
        lookingNumber = lookingNumber,
        vacancyTitle = vacancyTitle,
        town = address.town,
        street = address.street,
        house = address.house,
        company = company,
        previewText = experience.previewText,
        text = experience.text,
        publishedDate = publishedDate,
        isFavorites = isFavorites,
        short = salary.short,
        full = salary.full,
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        question = question,
    )
}

fun List<VacancyDto>.toListOfVacancy(): List<Vacancy> {
    return this.map {
        Vacancy(
            vacancyId = it.vacancyId,
            lookingNumber = it.lookingNumber,
            vacancyTitle = it.vacancyTitle,
            town = it.address.town,
            street = it.address.street,
            house = it.address.house,
            company = it.company,
            previewText = it.experience.previewText,
            text = it.experience.text,
            publishedDate = it.publishedDate,
            isFavorites = it.isFavorites,
            short = it.salary.short,
            full = it.salary.full,
            schedules = it.schedules,
            appliedNumber = it.appliedNumber,
            description = it.description,
            responsibilities = it.responsibilities,
            question = it.question,
        )
    }
}

fun VacancyListDto.toVacancyList(): VacancyList {
    return VacancyList(
        vacancyList = vacancyList.toListOfVacancy()
    )
}
