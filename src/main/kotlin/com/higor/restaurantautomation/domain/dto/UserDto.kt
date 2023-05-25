package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.utils.validators.Email
import jakarta.validation.constraints.NotBlank
import java.time.Instant
import java.util.UUID

data class UserDtoIn(
    @NotBlank(message = "Name is required")
    val name: String,

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    val email: String,

    @NotBlank(message = "Password is required")
    val password: String,

    @NotBlank(message = "Phone is required")
    val phone: String,

    @NotBlank(message = "Role is required")
    val role: Role,

    @NotBlank(message = "Company id is required")
    val companyId: UUID,
)

data class UserDtoOut(
    val id: UUID,
    val name: String,
    val email: String,
    val role: Role,
    val companyId: UUID,
    val createdAt: Instant,
) {
    companion object {
        fun from(other: UserModel): UserDtoOut {
            return UserDtoOut(
                id = other.id!!,
                name = other.name,
                email = other.email,
                role = other.role,
                companyId = other.companyId,
                createdAt = other.createdAt,
            )
        }
    }
}

data class UsersFilter(
    val email: String?,
    val name: String?,
    val role: Role?,
    val phone: String?,
)
