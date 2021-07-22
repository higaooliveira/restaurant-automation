package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.ProductDto
import com.higor.restaurantautomation.domain.dto.ProductPagedResponse
import com.higor.restaurantautomation.domain.dto.PromotionDto
import com.higor.restaurantautomation.domain.dto.UpdateProductDto
import com.higor.restaurantautomation.domain.service.contracts.ProductServiceContract
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ProductResource(
    @Autowired private val productService: ProductServiceContract
) {

    @GetMapping("/office/products/company")
    fun getAll(
        @RequestAttribute("companyId") companyId: UUID,
        pageable: Pageable
    ): ResponseEntity<ProductPagedResponse> {
        val productPagedResponse = this.getProductList(companyId, pageable)

        return ResponseEntity.ok(productPagedResponse)
    }

    @GetMapping("/products/company/{companyId}")
    fun getList(
        @PathVariable companyId: UUID,
        pageable: Pageable
    ): ResponseEntity<ProductPagedResponse> {
        val productPagedResponse = this.getProductList(companyId, pageable)

        return ResponseEntity.ok(productPagedResponse)
    }

    @GetMapping("/office/products/{productId}")
    fun getDetails(
        @RequestAttribute("companyId") companyId: UUID,
        @PathVariable productId: UUID
    ): ResponseEntity<ProductDto> {
        val product = this.productService.get(productId)
        return ResponseEntity.ok(product)
    }

    @GetMapping("/products/{productId}/company/{companyId}")
    fun getById(@PathVariable companyId: UUID, @PathVariable productId: UUID): ResponseEntity<ProductDto> {
        val product = this.productService.get(productId)
        return ResponseEntity.ok(product)
    }

    @PostMapping("/office/products")
    fun create(
        @RequestAttribute("companyId") companyId: UUID,
        @Valid @RequestBody productDto: ProductDto
    ): ResponseEntity<ProductDto> {
        val product = this.productService.create(productDto, companyId)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    @PostMapping("/office/products/promotion")
    fun createPromotion(@RequestBody promotionDto: PromotionDto): ResponseEntity<ProductDto> {
        val productWithPromotion = this.productService.createPromotion(promotionDto)

        return ResponseEntity.status(HttpStatus.CREATED).body(productWithPromotion)
    }

    @PutMapping("/office/products")
    fun update(@RequestBody productDto: UpdateProductDto): ResponseEntity<ProductDto> {
        val product = this.productService.update(productDto)

        return ResponseEntity.ok(product)
    }

    @DeleteMapping("/office/products/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        this.productService.delete(id)
        return ResponseEntity.noContent().build()
    }

    private fun getProductList(companyId: UUID, pageable: Pageable): ProductPagedResponse {
        return this.productService.getAll(companyId, pageable)
    }
}
