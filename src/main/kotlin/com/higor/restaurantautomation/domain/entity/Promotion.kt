package com.higor.restaurantautomation.domain.entity

import com.higor.restaurantautomation.domain.dto.PromotionDto
import com.higor.restaurantautomation.domain.dto.PromotionResponseDto
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

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
    val product: Product
) {
    fun toDto() = PromotionDto(
        id = this.id,
        productId = this.product.id!!,
        type = this.type,
        value = this.value,
        validUntil = this.validUntil
    )

    fun toResponseDto() = PromotionResponseDto(
        id = this.id!!,
        type = this.type,
        value = this.value,
        validUntil = this.validUntil
    )
}
