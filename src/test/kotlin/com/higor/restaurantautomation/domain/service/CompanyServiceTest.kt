package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.entity.Company
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import java.util.Optional
import java.util.UUID

@SpringBootTest
@ContextConfiguration(classes = [CompanyService::class])
class CompanyServiceTest {

    @MockBean
    lateinit var repository: CompanyRepository

    @Autowired
    lateinit var companyService: CompanyService

    @Test
    fun getCompanyTest() {
        val id = UUID.randomUUID()
        val expectedCompany = Optional.of(
            Company(
                id,
                "John Doe",
                "johndoe@mock.com",
                "123456",
                "123456",
                "123456"
            )
        )
        Mockito.`when`(this.repository.findById(id)).thenReturn(expectedCompany)

        val returnedCompany = this.companyService.getById(id)

        Assertions.assertEquals(expectedCompany.get(), returnedCompany)
        Assertions.assertEquals(expectedCompany.get().id, returnedCompany.id)
    }

    @Test
    fun createCompanyTest() {
        val id = UUID.randomUUID()
        val companyDto = CreateCompanyDto("John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedCompany = Company(id, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")

        BDDMockito.`when`(this.repository.save(any(Company::class.java))).thenReturn(expectedCompany)
        val returnedCompany = this.companyService.create(companyDto)

        Assertions.assertEquals(expectedCompany, returnedCompany)
    }

    @Test
    fun updateCompanyTest() {
        val id = UUID.randomUUID()
        val company = Optional.of(Company(id, "John Doe", "johndoe@mock.com", "123456", "123456", "123456"))
        val expectedUpdatedCompany = Company(id, "Foo bar", "foobar@mock.com", "78910", "78910", "78910")
        val updateCompanyDto = UpdateCompanyDto(id, "Foo bar", "foobar@mock.com", "78910", "78910")
        BDDMockito.`when`(this.repository.findById(id)).thenReturn(company)
        BDDMockito.`when`(this.repository.save(any(Company::class.java))).thenReturn(expectedUpdatedCompany)

        val returnedCompany = this.companyService.update(updateCompanyDto)
        Assertions.assertEquals(expectedUpdatedCompany, returnedCompany)
    }

    @Test
    fun deleteCompanyTest() {
        val id = UUID.randomUUID()
        val company = Optional.of(Company(id, "John Doe", "johndoe@mock.com", "123456", "123456", "123456"))
        BDDMockito.`when`(this.repository.findById(id)).thenReturn(company)
        this.companyService.delete(company.get().id)
        BDDMockito.verify(this.repository, times(1)).deleteById(company.get().id)
    }
}
