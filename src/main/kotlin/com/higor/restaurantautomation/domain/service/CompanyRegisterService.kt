package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.RegisterDto

interface CompanyRegisterService {
    fun execute(request: RegisterDto): AuthenticationDtoOut
}
