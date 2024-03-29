package com.higor.restaurantautomation.domain.dto.auth

import com.higor.restaurantautomation.utils.validators.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated

@Validated
data class RegisterDto(
    @NotBlank(message = "Name is required")
    val socialName: String,

    @NotBlank(message = "Name is required")
    val fantasyName: String,

    @NotBlank(message = "Phone is required")
    val phone: String,

    @NotBlank(message = "Document is required")
    val document: String,

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    val userEmail: String,

    @NotBlank(message = "user name is required")
    val userName: String,

    @NotBlank(message = "Password is required")
    val password: String,

)

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
