package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.company.CompanyRepository
import com.higor.restaurantautomation.domain.service.company.CompanyExistsService
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CompanyExistsServiceTest {
    private val companyRepository: CompanyRepository = mock {}

    private val companyExistsService: CompanyExistsService = CompanyExistsServiceImpl(companyRepository)

    @BeforeEach
    fun setup() {
        whenever(companyRepository.existsByDocument(Factory.companyEntity.document))
            .doReturn(true)

        whenever(companyRepository.existsByDocument(Factory.invalidDocument))
            .doReturn(false)
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
