package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.utils.validators.Email
import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class UserDtoIn(
    @NotBlank(message = "Name is required")
    val name: String,

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    val email: String,

    @NotBlank(message = "Password is required")
    val password: String,

    @NotBlank(message = "Role is required")
    val role: Role,

    @NotBlank(message = "Company id is required")
    val companyId: UUID,
)
