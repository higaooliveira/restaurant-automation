package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.adapters.entity.Role
import java.time.Instant
import java.util.UUID

data class UserDto(
    val name: String,
    val email: String,
    val password: String,
    val role: Role,
    val createdAt: Instant,
    val companyId: UUID,
    val updatedAt: Instant? = null,
)
