package com.higor.restaurantautomation.domain.DTO

import java.time.Instant

data class StandardError(val messages: HashSet<String>, val path: String, val date: Instant)