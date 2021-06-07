package com.higor.restaurantautomation.domain.entity

import com.higor.restaurantautomation.domain.dto.ProductDto
import java.time.LocalDate
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    private val name: String,
    val price: Double,
    private val quantity: Int,
    private val description: String,

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

    fun calculateTotal(quantity: Int): Double = this.price * quantity

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
