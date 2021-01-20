package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.DTO.CompanyDTO
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.respository.CompanyRepository
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
class CompanyService(@Autowired val companyRepository: CompanyRepository) {

    fun getCompany(id: Long): Company = companyRepository.findById(id).orElseThrow { ResourceNotFound("Resource Not Found for passe id")}


    fun create(companyDTO: CompanyDTO): Company {
        if (this.companyRepository.findByEmail(companyDTO.email) != null){
            throw ResourceAlreadyExists("Resource Already exists for the passed email")
        }

        val company = companyDTO.toEntity()
        company.encodePassword()

        return this.companyRepository.save(company)
    }
}
