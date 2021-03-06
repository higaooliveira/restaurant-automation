package com.higor.restaurantautomation.domain.dto

import java.util.UUID

data class CreateCompanyDto(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val document: String
)

data class CompanyResponse(
    val name: String,
    val email: String,
    val phone: String,
    val document: String
)

data class UpdateCompanyDto(
    var id: UUID? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val document: String? = null
)

data class UpdateCompanyPasswordDto(
    var id: UUID? = null,
    val password: String
)

data class CompanyLoginDto(
    val email: String,
    val password: String
)

data class CompanyLoggedInDto(
    val accessToken: String
)
