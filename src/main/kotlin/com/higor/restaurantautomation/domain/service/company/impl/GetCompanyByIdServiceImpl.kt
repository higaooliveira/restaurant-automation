package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.service.company.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetCompanyByIdServiceImpl(
    private val companyRepository: CompanyRepository,
) : GetCompanyByIdService {
    override fun execute(id: UUID): CompanyModel {
        return companyRepository
            .findById(id)
            .map(CompanyModel::from)
            .orElseThrow {
                ApiException(ApiErrorCodes.COMPANY_NOT_FOUND)
            }
    }
}
