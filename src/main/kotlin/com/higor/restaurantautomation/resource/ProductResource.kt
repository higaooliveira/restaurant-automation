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

    @GetMapping("/product")
    fun getAll(
        @RequestAttribute("companyId") companyId: UUID,
        pageable: Pageable
    ): ResponseEntity<ProductPagedResponse> {
        val productPagedResponse = this.productService.getAll(companyId, pageable)

        return ResponseEntity.ok(productPagedResponse)
    }

    @PostMapping("/product")
    fun create(
        @RequestAttribute("companyId") companyId: UUID,
        @Valid @RequestBody productDto: ProductDto
    ): ResponseEntity<ProductDto> {
        val product = this.productService.create(productDto, companyId)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    @PostMapping("/product/promotion")
    fun createPromotion(@RequestBody promotionDto: PromotionDto): ResponseEntity<ProductDto> {
        val productWithPromotion = this.productService.createPromotion(promotionDto)

        return ResponseEntity.status(HttpStatus.CREATED).body(productWithPromotion)
    }

    @PutMapping("/product")
    fun update(@RequestBody productDto: UpdateProductDto): ResponseEntity<ProductDto> {
        val product = this.productService.update(productDto)

        return ResponseEntity.ok(product)
    }

    @DeleteMapping("/product/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        this.productService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
