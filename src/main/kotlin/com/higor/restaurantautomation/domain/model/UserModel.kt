package com.higor.restaurantautomation.domain.model

import com.higor.restaurantautomation.adapters.entity.Role
import java.time.Instant
import java.util.UUID

data class UserModel(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val role: Role,
    val createdAt: Instant,
    val companyId: UUID,
    val updatedAt: Instant? = null,
) {

    companion object
}
