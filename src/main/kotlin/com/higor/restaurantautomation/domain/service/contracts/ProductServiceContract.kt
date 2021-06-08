package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.ProductDto
import com.higor.restaurantautomation.domain.dto.ProductPagedResponse
import com.higor.restaurantautomation.domain.dto.PromotionDto
import com.higor.restaurantautomation.domain.dto.UpdateProductDto
import com.higor.restaurantautomation.domain.entity.Product
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ProductServiceContract : Crudable<Product> {
    fun getAll(companyId: UUID, pageable: Pageable): ProductPagedResponse
    fun create(productDto: ProductDto, companyId: UUID): ProductDto
    fun update(productDto: UpdateProductDto): ProductDto
    fun createPromotion(promotionDto: PromotionDto): ProductDto
}
