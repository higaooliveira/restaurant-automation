package com.higor.restaurantautomation.domain.service.company

import com.higor.restaurantautomation.domain.model.CompanyModel
import java.util.UUID

interface GetCompanyByIdService {
    fun execute(id: UUID): CompanyModel
}