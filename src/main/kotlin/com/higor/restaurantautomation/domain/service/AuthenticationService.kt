package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.dto.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut

interface AuthenticationService {
    fun execute(request: AuthenticationDtoIn): AuthenticationDtoOut
}
