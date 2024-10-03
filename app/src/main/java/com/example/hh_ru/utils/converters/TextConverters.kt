package com.example.hh_ru.utils.converters

object TextConverters {
    fun getPeopleDeclinationByNumber(numberOfPeople: String): String {
        val people_01_5_9 = "человек"
        val people_2_4 = "человека"
        when(numberOfPeople.last()) {
            '0' -> return people_01_5_9
            '1' -> return people_01_5_9
            '2' -> return people_2_4
            '3' -> return people_2_4
            '4' -> return people_2_4
            '5' -> return people_01_5_9
            '6' -> return people_01_5_9
            '7' -> return people_01_5_9
            '8' -> return people_01_5_9
            '9' -> return people_01_5_9
            else -> return people_01_5_9
        }
    }

    fun getVacancyDeclinationByNumber(numberOfVacancies: String): String {
        val vacancy_01_5_9 = "вакансий"
        val vacancy_2_4 = "вакансии"
        when(numberOfVacancies.last()) {
            '0' -> return vacancy_01_5_9
            '1' -> return vacancy_01_5_9
            '2' -> return vacancy_2_4
            '3' -> return vacancy_2_4
            '4' -> return vacancy_2_4
            '5' -> return vacancy_01_5_9
            '6' -> return vacancy_01_5_9
            '7' -> return vacancy_01_5_9
            '8' -> return vacancy_01_5_9
            '9' -> return vacancy_01_5_9
            else -> return vacancy_01_5_9
        }
    }

    fun getWatchDeclinationByNumber(numberOfPeople: String): String {
        val watch_1 = "просматривает"
        val watch_0_2_9= "просматривают"
        when(numberOfPeople.last()) {
            '0' -> return watch_0_2_9
            '1' -> return watch_1
            '2' -> return watch_0_2_9
            '3' -> return watch_0_2_9
            '4' -> return watch_0_2_9
            '5' -> return watch_0_2_9
            '6' -> return watch_0_2_9
            '7' -> return watch_0_2_9
            '8' -> return watch_0_2_9
            '9' -> return watch_0_2_9
            else -> return watch_0_2_9
        }
    }
}