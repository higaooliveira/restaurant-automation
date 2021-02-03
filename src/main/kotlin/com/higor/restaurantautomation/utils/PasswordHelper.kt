package com.higor.restaurantautomation.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object PasswordHelper {

    fun encode(password: String): String = BCryptPasswordEncoder().encode(password)

}