package com.higor.restaurantautomation.domain.service.auth

import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.RegisterDto

interface RegisterService {
    fun execute(request: RegisterDto): AuthenticationDtoOut
}
