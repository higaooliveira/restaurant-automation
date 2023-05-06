package com.higor.restaurantautomation.utils

import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle

fun messageSource(key: String, params: Array<String> = emptyArray(), locale: Locale = Locale.getDefault()): String {
    val keyType = key.ifEmpty {
        "unavailable"
    }

    val pattern = ResourceBundle.getBundle("messages", locale).getString(keyType)
    return if (params.isEmpty()) pattern else MessageFormat.format(pattern, *params)
}
