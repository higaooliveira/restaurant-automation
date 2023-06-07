package com.higor.restaurantautomation.domain.dto.user

import com.higor.restaurantautomation.domain.model.user.Role
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
    val phone: String,
    val role: Role,
    val companyId: UUID,
    val createdAt: Instant,
) {

    companion object
}

data class UsersFilter(
    val email: String? = null,
    val name: String? = null,
    val role: Role? = null,
    val phone: String? = null,
)
