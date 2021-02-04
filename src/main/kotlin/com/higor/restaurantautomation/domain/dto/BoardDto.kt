package com.higor.restaurantautomation.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateBoardDto(
        val number: Long,

        @JsonProperty("company_id")
        val companyId: Long
)