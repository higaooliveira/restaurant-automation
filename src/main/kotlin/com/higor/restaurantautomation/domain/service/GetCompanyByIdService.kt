package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.model.CompanyModel
import java.util.UUID

interface GetCompanyByIdService {
    fun execute(id: UUID): CompanyModel
}