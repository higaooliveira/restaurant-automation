package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.utils.PasswordHelper
import com.higor.restaurantautomation.utils.validators.Email
import jakarta.validation.constraints.NotBlank

data class RegisterDto(
    @NotBlank(message = "Name is required")
    val name: String,

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    val email: String,

    @NotBlank(message = "Document is required")
    val document: String,

    @NotBlank(message = "Password is required")
    val password: String,

    @NotBlank(message = "Phone is required")
    val phone: String,
) {
    fun toModel(): CompanyModel {
        return CompanyModel(
            name = name,
            email = email,
            document = document,
            password = PasswordHelper.encode(password),
            phone = phone,
            updatedAt = null,
        )
    }
}

data class AuthenticationDtoIn(
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    val email: String,

    @NotBlank(message = "Password is required")
    val password: String,
)

data class AuthenticationDtoOut(
    val accessToken: String,
)
