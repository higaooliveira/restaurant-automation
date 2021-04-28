package com.higor.restaurantautomation.domain.dto

import java.util.UUID

data class CreateCompanyDto(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val document: String
)

data class UpdateCompanyDto(
    val id: UUID,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val document: String? = null
)

data class UpdateCompanyPasswordDto(
    val email: String,
    val password: String
)

data class CompanyLoginDto(
    val email: String,
    val password: String
)

data class CompanyLoggedInDto(
    val accessToken: String
)
