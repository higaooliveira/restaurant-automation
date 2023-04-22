package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.adapters.entity.Product
import com.higor.restaurantautomation.adapters.entity.Promotion
import com.higor.restaurantautomation.adapters.entity.PromotionType
import java.time.LocalDate
import java.util.UUID

data class PromotionDto(
    val id: UUID? = null,
    val productId: UUID,
    val type: PromotionType,
    val value: Double,
    val validUntil: LocalDate
) {
    fun toEntity(product: Product) = Promotion(
        type = this.type,
        value = this.value,
        createdAt = LocalDate.now(),
        validUntil = this.validUntil,
        product = product
    )
}

data class UpdatePromotionDto(
    val id: UUID,
    val type: PromotionType,
    val value: Double,
    val validUntil: LocalDate
)

data class PromotionResponseDto(
    val id: UUID,
    val type: PromotionType,
    val value: Double,
    val validUntil: LocalDate
)
