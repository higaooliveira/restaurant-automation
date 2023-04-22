package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.dto.UpdateCompanyDtoIn
import com.higor.restaurantautomation.domain.model.CompanyModel

interface UpdateCompanyService {

    fun execute(request: UpdateCompanyDtoIn): CompanyModel
}
