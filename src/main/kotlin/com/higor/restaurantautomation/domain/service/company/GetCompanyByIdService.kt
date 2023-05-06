package com.higor.restaurantautomation.domain.service.company

import com.higor.restaurantautomation.adapters.entity.Company
import java.util.UUID

interface GetCompanyByIdService {
    fun execute(id: UUID): Company
}
