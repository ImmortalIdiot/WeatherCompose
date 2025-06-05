package com.immortalidiot.weathercompose.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateManager {
    fun toDateString(timestamp: Long): String {
        val simpleFormatter = SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.getDefault())
        val formated = simpleFormatter.format(Date(timestamp * Constants.MILLISECONDS_PER_SECONDS))
        return formated.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
}
