package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.configuration.security.CompanyDetails
import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.UUID

@Component
@Service
class CompanyService(@Autowired val companyRepository: CompanyRepository) : CompanyServiceContract {

    override fun getById(id: UUID): Company = this.companyRepository
        .findById(id)
        .orElseThrow { ResourceNotFound("Resource Not Found for passed id") }

    override fun create(createDto: CreateCompanyDto): Company {
        if (this.companyExistsByEmail(createDto.email)) {
            throw ResourceAlreadyExists("Resource Already exists for the passed email")
        }

        val company = MapperUtils.convert<CreateCompanyDto, Company>(createDto)
        company.encodePassword()

        return this.companyRepository.save(company)
    }

    override fun update(updateDto: UpdateCompanyDto): Company {
        val company = this.getById(updateDto.id!!)
        MapperUtils.merge(updateDto, company)
        return this.companyRepository.save(company)
    }

    override fun updatePassword(updateDto: UpdateCompanyPasswordDto): Company {
        val company = this.getById(updateDto.id!!)
        MapperUtils.merge(updateDto, company)
        company.encodePassword()
        return this.companyRepository.save(company)
    }

    override fun delete(id: UUID) {
        try {
            this.companyRepository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            throw ResourceNotFound("Resource Not Found for passed id")
        }
    }

    override fun loadUserByUsername(email: String): CompanyDetails {
        val company = companyRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)

        return CompanyDetails(company)
    }

    private fun companyExistsByEmail(email: String): Boolean = this.companyRepository
        .findByEmail(email) != null
}
