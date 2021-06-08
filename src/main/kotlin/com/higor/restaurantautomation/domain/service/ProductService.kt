package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.ProductRepository
import com.higor.restaurantautomation.domain.dto.ProductDto
import com.higor.restaurantautomation.domain.dto.ProductPagedResponse
import com.higor.restaurantautomation.domain.dto.PromotionDto
import com.higor.restaurantautomation.domain.dto.UpdateProductDto
import com.higor.restaurantautomation.domain.entity.Product
import com.higor.restaurantautomation.domain.entity.PromotionType
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.domain.service.contracts.DiscountCalculatorStrategy
import com.higor.restaurantautomation.domain.service.contracts.ProductServiceContract
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val companyService: CompanyServiceContract
) : ProductServiceContract {

    override fun getById(id: UUID): Product = this.productRepository
        .findById(id)
        .orElseThrow { ResourceNotFound("Resource Not Found for passed id") }

    override fun getAll(companyId: UUID, pageable: Pageable): ProductPagedResponse {
        val pagedProducts = this.productRepository.findAllByCompanyId(companyId, pageable)
        val productsList = pagedProducts.toList().map { it.toDto() }.map { product ->

            product.promotion?.let {
                val discountCalculator = getDiscountCalculator(it.type)
                val priceWithDiscount = calculateDiscount(discountCalculator, it.value, product.price)

                return@map product.copy(price = priceWithDiscount)
            }

            product
        }

        return ProductPagedResponse(
            products = productsList,
            page = pagedProducts.pageable.pageNumber,
            size = pagedProducts.size,
            totalPages = pagedProducts.totalPages,
            lastPage = pagedProducts.isLast
        )
    }

    override fun create(productDto: ProductDto, companyId: UUID): ProductDto {
        val company = this.companyService.getById(companyId)

        val product = productDto.toEntity(company)

        return this.productRepository.save(product).toDto()
    }

    override fun update(productDto: UpdateProductDto): ProductDto {
        val product = this.getById(productDto.id)

        MapperUtils.merge(productDto, product)
        this.productRepository.save(product)
        return product.toDto()
    }

    override fun createPromotion(promotionDto: PromotionDto): ProductDto {
        val product = getById(promotionDto.productId)
        val promotion = promotionDto.toEntity(product)
        product.addPromotion(promotion)

        return this.productRepository.save(product).toDto()
    }

    override fun delete(id: UUID) {
        try {
            this.productRepository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            throw ResourceNotFound("Resource Not Found for passed id")
        }
    }

    private fun calculateDiscount(
        discountCalculator: DiscountCalculatorStrategy,
        discount: Double,
        price: Double
    ): Double = discountCalculator.calculate(discount, price)

    private fun getDiscountCalculator(promotionType: PromotionType): DiscountCalculatorStrategy {
        return when (promotionType) {
            PromotionType.PERCENTAGE -> PercentageDiscountCalculator
            PromotionType.REAL -> RealDiscountCalculator
        }
    }
}
