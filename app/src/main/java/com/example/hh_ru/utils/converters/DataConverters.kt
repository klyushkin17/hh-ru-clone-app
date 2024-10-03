package com.example.hh_ru.utils.converters

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DataConverters {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateString(dateString: String): String {
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, inputFormat)
        val outputFormat = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        return date.format(outputFormat)
    }

    fun formatDateStringLowVersion(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-d90d")
        val date = inputFormat.parse(dateString)
        val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))
        return outputFormat.format(date)
    }
}