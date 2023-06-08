package com.higor.restaurantautomation.domain.service.product

import com.higor.restaurantautomation.domain.model.product.ProductModel
import java.util.UUID

interface GetProductByIdService {
    fun execute(id: UUID): ProductModel
}
