package com.higor.restaurantautomation.domain.dto

import java.util.UUID

data class CreateBoardDto(
    val number: Long,
    var companyId: UUID? = null
)
