package com.higor.restaurantautomation.utils.extensions

import com.higor.restaurantautomation.adapters.entity.Product
import com.higor.restaurantautomation.domain.dto.product.ProductDtoOut
import com.higor.restaurantautomation.domain.model.product.ProductModel

fun ProductModel.Companion.from(other: Product): ProductModel {
    return ProductModel(
        id = other.id,
        name = other.name,
        description = other.description,
        price = other.price,
        type = other.type,
        companyId = other.company.id!!,
        createdDate = other.createdDate,
        lastModifiedDate = other.lastModifiedDate,
    )
}

fun ProductDtoOut.Companion.from(other: ProductModel): ProductDtoOut {
    return ProductDtoOut(
        id = other.id!!,
        name = other.name,
        description = other.description,
        price = other.price,
        type = other.type,
        createdDate = other.createdDate,
        lastModifiedDate = other.lastModifiedDate,
        companyId = other.companyId,
    )
}
