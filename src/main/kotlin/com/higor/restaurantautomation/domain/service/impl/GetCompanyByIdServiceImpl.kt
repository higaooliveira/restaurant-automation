package com.higor.restaurantautomation.domain.service.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.service.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetCompanyByIdServiceImpl(
    private val companyRepository: CompanyRepository,
) : GetCompanyByIdService {
    override fun execute(id: UUID): CompanyModel {
        val company = companyRepository
            .findById(id)
            .orElseThrow { ApiException("Company Not found", statusCode = HttpStatus.NOT_FOUND) }

        return CompanyModel.fromEntity(company)
    }
}
