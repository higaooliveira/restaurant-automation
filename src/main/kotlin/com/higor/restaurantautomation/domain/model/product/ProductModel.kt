package com.higor.restaurantautomation.domain.model.product

import java.time.Instant
import java.util.UUID

data class ProductModel(
    val id: UUID? = null,
    val name: String,
    val description: String? = null,
    val type: ProductType,
    val price: Long,
    val companyId: UUID,
    val createdDate: Instant,
    val lastModifiedDate: Instant? = null,
) {
    companion object
}
