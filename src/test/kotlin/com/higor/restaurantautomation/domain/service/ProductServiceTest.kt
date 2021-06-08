package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.ProductRepository
import com.higor.restaurantautomation.domain.entity.Product
import com.higor.restaurantautomation.domain.entity.createProduct
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.domain.service.contracts.ProductServiceContract
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import java.util.Optional
import java.util.UUID

@SpringBootTest
@ContextConfiguration(classes = [ProductService::class])
class ProductServiceTest {

    @MockBean
    lateinit var repository: ProductRepository

    @MockBean
    lateinit var companyService: CompanyServiceContract

    @Autowired
    lateinit var service: ProductServiceContract

    @Test
    fun `Given a valid product Id, when try to get a product By Id, Then a valid product should return`() {
        val id = UUID.randomUUID()
        val expectedProduct = Optional.of(createProduct(id = id))
        Mockito.`when`(repository.findById(id)).thenReturn(expectedProduct)

        val actualProduct = service.getById(id)

        assert(expectedProduct.get() == actualProduct)
    }

    @Test
    fun `Given an invalid product Id, when try to get a product By Id, Then ResourceNotFoundException should throws`() {
        val id = UUID.randomUUID()
        val expectedProduct = Optional.empty<Product>()
        Mockito.`when`(repository.findById(id)).thenReturn(expectedProduct)

        assertThrows<ResourceNotFound> {
            service.getById(id)
        }
    }

    @Test
    fun getAll() {
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {
    }

    @Test
    fun createPromotion() {
    }

    @Test
    fun delete() {
    }
}
