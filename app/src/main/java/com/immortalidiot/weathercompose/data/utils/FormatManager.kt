package com.immortalidiot.weathercompose.data.utils

import java.util.Locale

object FormatManager {
    fun uppercaseFirst(string: String): String {
        return if(string.isEmpty()) {
            string
        } else {
            string.replaceFirstChar {
                if (it.isLowerCase()) {
                    it.titlecase(Locale.getDefault())
                } else {
                    it.toString()
                }
            }
        }
    }
}
