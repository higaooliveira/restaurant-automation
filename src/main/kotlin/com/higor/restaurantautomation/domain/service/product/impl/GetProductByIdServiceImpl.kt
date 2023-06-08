package com.higor.restaurantautomation.domain.service.product.impl

import com.higor.restaurantautomation.adapters.repository.product.ProductRepository
import com.higor.restaurantautomation.domain.model.product.ProductModel
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.product.GetProductByIdService
import com.higor.restaurantautomation.utils.extensions.from
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetProductByIdServiceImpl(
    private val productRepository: ProductRepository,
) : GetProductByIdService {

    override fun execute(id: UUID): ProductModel {
        val product = productRepository
            .findById(id)
            .map(ProductModel::from)
            .orElseThrow {
                ApiException(
                    errorCode = ApiErrorCodes.PRODUCT_NOT_FOUND,
                    params = arrayOf(id.toString()),
                )
            }

        // calculate discount for promotions

        return product
    }
}
