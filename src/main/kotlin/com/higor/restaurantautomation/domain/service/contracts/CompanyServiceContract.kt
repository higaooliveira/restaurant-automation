package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company

interface CompanyServiceContract : Crudable<Company>{
    fun updatePassword(updateDto: UpdateCompanyPasswordDto): Company
    fun create(createDto: CreateCompanyDto): Company
    fun update(updateDto: UpdateCompanyDto): Company

}