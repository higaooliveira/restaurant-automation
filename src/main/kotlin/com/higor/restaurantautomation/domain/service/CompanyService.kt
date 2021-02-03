package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.respository.CompanyRepository
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
class CompanyService(@Autowired val companyRepository: CompanyRepository) {

    fun getCompany(id: Long): Company = this.companyRepository
            .findById(id)
            .orElseThrow { ResourceNotFound("Resource Not Found for passed id")}


    fun getAllCompanies(): List<Company> = this.companyRepository
            .findAll()

    fun create(createCompanyDto: CreateCompanyDto): Company {
        if (this.companyExistsByEmail(createCompanyDto.email)){
            throw ResourceAlreadyExists("Resource Already exists for the passed email")
        }

        val company = MapperUtils.convert<CreateCompanyDto, Company>(createCompanyDto)
        company.encodePassword()

        return this.companyRepository.save(company)
    }

    fun update(updateCompanyDto: UpdateCompanyDto): Company {
        val company = this.getCompany(updateCompanyDto.id)
        MapperUtils.merge(updateCompanyDto, company)
        return this.companyRepository.save(company)
    }

    fun updatePassword(updateCompanyPasswordDto: UpdateCompanyPasswordDto): Company {
        val company = this.getCompanyByEmail(updateCompanyPasswordDto.email)
        MapperUtils.merge(updateCompanyPasswordDto, company)
        company.encodePassword()
        return this.companyRepository.save(company)
    }

    fun delete(id: Long) {
        try {
            this.companyRepository.deleteById(id)
        }catch (ex: EmptyResultDataAccessException){
            throw ResourceNotFound("Resource Not Found for passed id")
        }
    }

    private fun getCompanyByEmail(email: String): Company = this.companyRepository
            .findByEmail(email) ?: throw ResourceNotFound("Resource Not Found for passed email")

    private fun companyExistsByEmail(email: String): Boolean = this.companyRepository
            .findByEmail(email) != null
}
