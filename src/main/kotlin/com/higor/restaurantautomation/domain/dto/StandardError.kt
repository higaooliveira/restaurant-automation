package com.higor.restaurantautomation.domain.dto

import java.time.Instant

data class StandardError(
    val messages: HashSet<String>,
    val path: String,
    val date: Instant
)
