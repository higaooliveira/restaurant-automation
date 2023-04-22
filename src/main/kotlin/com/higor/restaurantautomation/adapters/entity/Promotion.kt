package com.higor.restaurantautomation.adapters.entity

import com.higor.restaurantautomation.domain.dto.PromotionDto
import com.higor.restaurantautomation.domain.dto.PromotionResponseDto
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "promotion")
class Promotion(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    val type: PromotionType,

    val value: Double,

    val createdAt: LocalDate,

    val validUntil: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,
) {
    fun toDto() = PromotionDto(
        id = this.id,
        productId = this.product.id!!,
        type = this.type,
        value = this.value,
        validUntil = this.validUntil,
    )

    fun toResponseDto() = PromotionResponseDto(
        id = this.id!!,
        type = this.type,
        value = this.value,
        validUntil = this.validUntil,
    )
}
