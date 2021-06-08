package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.entity.Product
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero

data class ProductDto(

    val id: UUID? = null,

    @field:NotBlank
    @field:NotEmpty
    val name: String,

    @field:PositiveOrZero
    val price: Double,
    @field:PositiveOrZero
    val quantity: Int,
    val description: String,
    val promotion: PromotionResponseDto? = null

) {
    fun toEntity(company: Company) = Product(
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        description = this.description,
        company = company
    )
}

data class ProductPagedResponse(
    val products: List<ProductDto>,
    val page: Int,
    val size: Int,
    val totalPages: Int,
    val lastPage: Boolean
)

data class UpdateProductDto(
    val id: UUID,
    val name: String?,
    val price: Double?,
    val quantity: Int?,
    val description: String?,
)
