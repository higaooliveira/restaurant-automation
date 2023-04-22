package com.higor.restaurantautomation.domain.dto

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class CompanyDtoOut(
    val id: UUID,
    val name: String,
    val email: String,
    val document: String,
    val phone: String,
)

data class UpdateCompanyDtoIn(
    @NotBlank(message = "Id must be provided")
    val id: UUID,
    val name: String?,
    val email: String?,
    val document: String?,
    val phone: String?,
)
