package com.higor.restaurantautomation.adapters.entity

import com.higor.restaurantautomation.domain.dto.ProductDto
import java.time.LocalDate
import java.util.UUID
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val name: String,
    val price: Double,
    val quantity: Int,
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    val company: Company,

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "product"
    )
    val promotion: MutableSet<Promotion> = HashSet()
) {

    fun toDto(price: Double? = null): ProductDto {
        val promotion = getValidPromotion()

        return ProductDto(
            id = this.id,
            name = this.name,
            price = price ?: this.price,
            quantity = this.quantity,
            description = this.description,
            promotion = promotion?.toResponseDto()
        )
    }

    fun addPromotion(promotion: Promotion) {
        this.promotion.add(promotion)
    }

    private fun getValidPromotion() = this.promotion.firstOrNull {
        val now = LocalDate.now()
        it.validUntil.isEqual(now) || it.validUntil.isAfter(now)
    }
}
