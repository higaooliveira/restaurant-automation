package com.higor.restaurantautomation.controller.product

import com.higor.restaurantautomation.domain.dto.product.ProductDtoOut
import com.higor.restaurantautomation.domain.service.product.GetProductByIdService
import com.higor.restaurantautomation.utils.extensions.from
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(ProductController.BASE_ENDPOINT)
@Validated
class ProductController(
    private val getProductByIdService: GetProductByIdService,
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<ProductDtoOut> {
        val product = getProductByIdService.execute(id)

        return ResponseEntity.ok(ProductDtoOut.from(product))
    }

    companion object {
        const val BASE_ENDPOINT = "/api/v1/office/product"
    }
}
