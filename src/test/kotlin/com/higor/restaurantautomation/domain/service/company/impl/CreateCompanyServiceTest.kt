package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.company.CompanyRepository
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.service.company.CompanyExistsService
import com.higor.restaurantautomation.domain.service.company.CreateCompanyService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CreateCompanyServiceTest {

    private val companyRepository: CompanyRepository = mock {}
    private val companyExistsService: CompanyExistsService = mock {}
    private val createCompanyService: CreateCompanyService = CreateCompanyServiceImpl(companyRepository, companyExistsService)

    @BeforeEach
    fun setup() {
        whenever(companyExistsService.execute(Factory.invalidDocument))
            .doReturn(true)

        whenever(companyExistsService.execute(Factory.companyEntity.document))
            .doReturn(false)

        whenever(companyRepository.save(Mockito.any()))
            .doReturn(Factory.companyEntity)
    }

    @Test
    fun `Should save companyEntity`() {
        val actualId = createCompanyService.execute(CompanyModel.from(Factory.companyEntity))

        assertEquals(Factory.companyEntity.id, actualId)
    }

    @Test
    fun `Should throw ApiException when try to save a company with an existent document`() {
        val exception = assertThrows<ApiException> {
            createCompanyService.execute(Factory.invalidCompanyModel)
        }

        val expectedMessage = "Company already exists"

        assertEquals(expectedMessage, exception.localizedMessage)
    }
}
