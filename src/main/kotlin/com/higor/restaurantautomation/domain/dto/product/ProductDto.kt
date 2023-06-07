package com.higor.restaurantautomation.domain.dto.product

import com.higor.restaurantautomation.domain.model.product.ProductType
import java.util.UUID

data class ProductDtoIn(
    val name: String,
    val price: Long,
    val description: String,
    val type: ProductType,
    val companyId: UUID,
)

data class ProductDtoOut(
    val id: UUID,
    val price: Long,
    val description: String,
    val type: ProductType,
)
