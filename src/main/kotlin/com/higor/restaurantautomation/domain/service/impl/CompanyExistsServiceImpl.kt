package com.higor.restaurantautomation.domain.service.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.service.CompanyExistsService
import org.springframework.stereotype.Service

@Service
class CompanyExistsServiceImpl(
    private val companyRepository: CompanyRepository,
) : CompanyExistsService {
    override fun execute(email: String, document: String): Boolean {
        return companyRepository.findByEmailOrDocument(email, document) != null
    }
}
