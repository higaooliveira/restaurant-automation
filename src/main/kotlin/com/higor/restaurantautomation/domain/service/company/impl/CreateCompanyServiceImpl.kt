package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.service.company.CompanyExistsService
import com.higor.restaurantautomation.domain.service.company.CreateCompanyService
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateCompanyServiceImpl(
    private val companyRepository: CompanyRepository,
    private val companyExistsService: CompanyExistsService,
) : CreateCompanyService {

    override fun execute(company: CompanyModel): UUID {
        if (companyExistsService.execute(company.document)) {
            throw ApiException(ApiErrorCodes.COMPANY_EXISTS)
        }

        val savedCompany = companyRepository.save(company.toEntity())

        return savedCompany.id!!
    }
}
