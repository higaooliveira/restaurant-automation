package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.service.company.CreateCompanyService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateCompanyServiceImpl(
    private val companyRepository: CompanyRepository,
) : CreateCompanyService {

    override fun execute(company: CompanyModel): UUID {
        val savedCompany = companyRepository.save(company.toEntity())

        return savedCompany.id!!
    }
}
