package com.higor.restaurantautomation.domain.model

import java.time.Instant
import java.util.UUID

data class CompanyModel(
    val id: UUID? = null,
    val socialName: String,
    val fantasyName: String,
    val document: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
) {

    companion object
}
