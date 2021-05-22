package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.ProductRepository
import com.higor.restaurantautomation.domain.dto.ProductDto
import com.higor.restaurantautomation.domain.dto.UpdateProductDto
import com.higor.restaurantautomation.domain.entity.Product
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.domain.service.contracts.ProductServiceContract
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val companyService: CompanyServiceContract
) : ProductServiceContract {

    override fun getById(id: UUID): Product =
        this.productRepository
            .findById(id)
            .orElseThrow { ResourceNotFound("Resource Not Found for passed id") }

    override fun getAll(companyId: UUID): List<ProductDto> {
        val products = this.productRepository.findAllByCompanyId(companyId)

        return products.map { it.toDto() }
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

    override fun delete(id: UUID) {
        try {
            this.productRepository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            throw ResourceNotFound("Resource Not Found for passed id")
        }
    }
}
