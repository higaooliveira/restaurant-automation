package com.higor.restaurantautomation.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object PasswordHelper {
    private val encoder = BCryptPasswordEncoder()

    fun encode(password: String): String = encoder.encode(password)
}
