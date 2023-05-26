package com.higor.restaurantautomation.domain.service.company.impl

import com.higor.restaurantautomation.adapters.repository.company.CompanyRepository
import com.higor.restaurantautomation.domain.service.company.CompanyExistsService
import org.springframework.stereotype.Service

@Service
class CompanyExistsServiceImpl(
    private val companyRepository: CompanyRepository,
) : CompanyExistsService {
    override fun execute(document: String): Boolean {
        return companyRepository.existsByDocument(document)
    }
}
