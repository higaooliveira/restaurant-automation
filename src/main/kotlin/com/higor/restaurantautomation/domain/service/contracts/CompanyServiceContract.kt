package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.security.core.userdetails.UserDetailsService

interface CompanyServiceContract : Crudable<Company>, UserDetailsService {
    fun updatePassword(updateDto: UpdateCompanyPasswordDto): Company
    fun create(createDto: CreateCompanyDto): Company
    fun update(updateDto: UpdateCompanyDto): Company
}
