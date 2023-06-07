package com.higor.restaurantautomation.domain.service.auth

import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoOut

interface AuthenticationService {
    fun execute(request: AuthenticationDtoIn): AuthenticationDtoOut
}
