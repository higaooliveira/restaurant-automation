package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.company.CompanyRepository
import com.higor.restaurantautomation.domain.service.company.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional

class GetCompanyByIdServiceTest {
    private val companyRepository: CompanyRepository = mock { }

    private val getCompanyByIdService: GetCompanyByIdService = GetCompanyByIdServiceImpl(companyRepository)

    private val entity = Factory.companyEntity

    @BeforeEach
    fun setup() {
        whenever(companyRepository.findById(entity.id!!))
            .doReturn(Optional.of(entity))

        whenever(companyRepository.findById(Factory.invalidId))
            .doReturn(Optional.empty())
    }

    @Test
    fun `Should return a valid company entity when try to get by id`() {
        val actualEntity = getCompanyByIdService.execute(entity.id!!)

        assertEquals(entity.id, actualEntity.id)
        assertEquals(entity.socialName, actualEntity.socialName)
        assertEquals(entity.fantasyName, actualEntity.fantasyName)
        assertEquals(entity.document, actualEntity.document)
    }

    @Test
    fun `Should throw ApiException when try to get by id`() {
        val exception = assertThrows(ApiException::class.java) {
            getCompanyByIdService.execute(Factory.invalidId)
        }

        val expectedMessage = "Company not found"

        assertEquals(expectedMessage, exception.localizedMessage)
    }
}
