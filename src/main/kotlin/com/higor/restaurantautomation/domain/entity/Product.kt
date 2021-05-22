package com.higor.restaurantautomation.domain.entity

import com.higor.restaurantautomation.domain.dto.ProductDto
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
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
    val company: Company
) {

    fun calculateTotal(quantity: Int): Double = this.price * quantity

    fun toDto() = ProductDto(
        id = this.id,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        description = this.description
    )
}
