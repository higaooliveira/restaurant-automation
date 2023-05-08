package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.service.company.CompanyExistsService
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CompanyExistsServiceTest {
    private val companyRepository: CompanyRepository = Mockito.mock(CompanyRepository::class.java)

    private val companyExistsService: CompanyExistsService = CompanyExistsServiceImpl(companyRepository)

    @BeforeEach
    fun setup() {
        Mockito
            .`when`(companyRepository.existsByDocument(Factory.companyEntity.document))
            .thenReturn(true)

        Mockito
            .`when`(companyRepository.existsByDocument(Factory.invalidDocument))
            .thenReturn(false)
    }

    @Test
    fun `Should return true when try to validate if company exists by document`() {
        val result = companyExistsService.execute(Factory.companyEntity.document)

        assertEquals(true, result)
    }

    @Test
    fun `Should return false when try to validate if company exists by document`() {
        val result = companyExistsService.execute(Factory.invalidDocument)
        assertEquals(false, result)
    }
}
