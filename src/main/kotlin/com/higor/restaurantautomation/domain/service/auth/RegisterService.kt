package com.higor.restaurantautomation.domain.service.auth

import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.auth.RegisterDto

interface RegisterService {
    fun execute(request: RegisterDto): AuthenticationDtoOut
}
