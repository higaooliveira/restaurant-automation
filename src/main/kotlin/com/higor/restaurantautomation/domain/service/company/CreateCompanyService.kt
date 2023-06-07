package com.higor.restaurantautomation.domain.service.company

import com.higor.restaurantautomation.domain.model.company.CompanyModel
import java.util.UUID

interface CreateCompanyService {
    fun execute(company: CompanyModel): UUID
}
