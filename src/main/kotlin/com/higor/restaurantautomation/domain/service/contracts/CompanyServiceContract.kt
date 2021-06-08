package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.CompanyResponse
import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.security.core.userdetails.UserDetailsService

interface CompanyServiceContract : Crudable<Company>, UserDetailsService {
    fun updatePassword(updateDto: UpdateCompanyPasswordDto): CompanyResponse
    fun create(createDto: CreateCompanyDto): CompanyResponse
    fun update(updateDto: UpdateCompanyDto): CompanyResponse
}
