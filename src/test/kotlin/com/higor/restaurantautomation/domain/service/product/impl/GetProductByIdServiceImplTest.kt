package com.higor.restaurantautomation.domain.service.product.impl

import com.higor.restaurantautomation.adapters.repository.product.ProductRepository
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional
import java.util.UUID

class GetProductByIdServiceImplTest {
    private val repository: ProductRepository = mock {}

    private val service = GetProductByIdServiceImpl(repository)

    @Test
    fun `should return product model when product exists`() {
        val productMock = Factory.productEntity
        val productId = productMock.id!!

        whenever(repository.findById(productId))
            .doReturn(Optional.of(productMock))

        // Act
        val result = service.execute(productId)

        // Assert

        assertEquals(result.id, productMock.id)
        assertEquals(result.name, productMock.name)
        assertEquals(result.price, productMock.price)
        assertEquals(result.companyId, productMock.company.id)
    }

    @Test
    fun `should throw ApiException when product does not exist`() {
        val productId = UUID.randomUUID()
        whenever(repository.findById(productId))
            .doReturn(Optional.empty())

        val expectedMessage = "Product not found with id: $productId"
        val exception = assertThrows(ApiException::class.java) {
            service.execute(productId)
        }

        assertEquals(expectedMessage, exception.localizedMessage)
    }
}
