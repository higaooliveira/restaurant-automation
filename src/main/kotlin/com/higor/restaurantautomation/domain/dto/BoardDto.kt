package com.higor.restaurantautomation.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class CreateBoardDto(
    val number: Long,

    @JsonProperty("company_id")
    val companyId: UUID
)
