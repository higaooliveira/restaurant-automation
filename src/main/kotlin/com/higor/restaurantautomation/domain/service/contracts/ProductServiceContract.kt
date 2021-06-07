package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.ProductDto
import com.higor.restaurantautomation.domain.dto.PromotionDto
import com.higor.restaurantautomation.domain.dto.UpdateProductDto
import com.higor.restaurantautomation.domain.entity.Product
import java.util.UUID

interface ProductServiceContract : Crudable<Product> {

    fun getAll(companyId: UUID): List<ProductDto>
    fun create(productDto: ProductDto, companyId: UUID): ProductDto
    fun update(productDto: UpdateProductDto): ProductDto
    fun createPromotion(promotionDto: PromotionDto): ProductDto
}
