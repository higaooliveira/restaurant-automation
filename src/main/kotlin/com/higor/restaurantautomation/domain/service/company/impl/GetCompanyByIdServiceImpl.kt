package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.company.CompanyRepository
import com.higor.restaurantautomation.domain.model.company.CompanyModel
import com.higor.restaurantautomation.domain.service.company.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.utils.extensions.from
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetCompanyByIdServiceImpl(
    private val companyRepository: CompanyRepository,
) : GetCompanyByIdService {

    @Cacheable(cacheNames = ["companyCache"], key = "#id")
    override fun execute(id: UUID): CompanyModel {
        return companyRepository
            .findById(id)
            .map(CompanyModel.Companion::from)
            .orElseThrow {
                ApiException(ApiErrorCodes.COMPANY_NOT_FOUND)
            }
    }
}
