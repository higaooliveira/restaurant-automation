package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.respository.CompanyRepository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class CompanyServiceTest {

    @MockBean
    lateinit var repository: CompanyRepository

    @Autowired
    lateinit var companyService: CompanyService

    @Test
    fun getCompanyTest() {
        val expectedCompany = Optional.of(
                Company(
                        1L,
                        "John Doe",
                        "johndoe@mock.com",
                        "123456",
                        "123456",
                        "123456"
                )
        )
        BDDMockito.`when`(this.repository.findById(1L)).thenReturn(expectedCompany)

        val returnedCompany = this.companyService.getById(1L)
        Assertions.assertEquals(expectedCompany.get(), returnedCompany)
        Assertions.assertEquals(expectedCompany.get().id, returnedCompany.id)
    }

    @Test
    fun getAllCompaniesTest() {
        val expectedCompaniesList = listOf(
                Company(1L,"John Doe","johndoe@mock.com","123456","123456","123456"),
                Company(1L,"John Doe","johndoe@mock.com","123456","123456","123456")
        )
        BDDMockito.`when`(this.repository.findAll()).thenReturn(expectedCompaniesList)

        val companiesList = this.companyService.getAll()
        Assertions.assertEquals(expectedCompaniesList, companiesList)
    }

    @Test
    fun createCompanyTest() {
        val companyDto = CreateCompanyDto("John Doe","johndoe@mock.com","123456","123456","123456")
        val expectedCompany = Company(1L,"John Doe","johndoe@mock.com","123456","123456","123456")

        BDDMockito.`when`(this.repository.save(any(Company::class.java))).thenReturn(expectedCompany)
        val returnedCompany = this.companyService.create(companyDto)

        Assertions.assertEquals(expectedCompany, returnedCompany)
    }

    @Test
    fun updateCompanyTest(){
        val company = Optional.of(Company(1L, "John Doe","johndoe@mock.com","123456","123456","123456"))
        val expectedUpdatedCompany = Company(1L, "Foo bar","foobar@mock.com","78910","78910","78910")
        val updateCompanyDto = UpdateCompanyDto(1L, "Foo bar","foobar@mock.com","78910","78910")
        BDDMockito.`when`(this.repository.findById(1L)).thenReturn(company)
        BDDMockito.`when`(this.repository.save(any(Company::class.java))).thenReturn(expectedUpdatedCompany)

        val returnedCompany = this.companyService.update(updateCompanyDto)
        Assertions.assertEquals(expectedUpdatedCompany, returnedCompany)
    }

    @Test
    fun deleteCompanyTest(){
        val company = Optional.of(Company(1L, "John Doe","johndoe@mock.com","123456","123456","123456"))
        BDDMockito.`when`(this.repository.findById(1L)).thenReturn(company)
        this.companyService.delete(company.get().id!!)
        BDDMockito.verify(this.repository, times(1)).deleteById(company.get().id!!)
    }
}