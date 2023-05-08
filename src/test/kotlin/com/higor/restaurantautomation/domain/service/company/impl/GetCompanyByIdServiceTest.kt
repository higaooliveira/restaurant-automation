package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.service.company.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.utils.factories.Factory
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GetCompanyByIdServiceTest {
    private val companyRepository: CompanyRepository = Mockito.mock(CompanyRepository::class.java)

    private val getCompanyByIdService: GetCompanyByIdService = GetCompanyByIdServiceImpl(companyRepository)

    private val entity = Factory.companyEntity

    @BeforeEach
    fun setup() {
        Mockito
            .`when`(companyRepository.getReferenceById(entity.id!!))
            .thenReturn(entity)

        Mockito
            .`when`(companyRepository.getReferenceById(Factory.invalidId))
            .thenThrow(EntityNotFoundException::class.java)
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
