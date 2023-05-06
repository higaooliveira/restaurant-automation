package com.higor.restaurantautomation.domain.service.auth.impl

import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.configuration.security.UserDetailsImpl
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.service.auth.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil,
) : AuthenticationService {
    override fun execute(request: AuthenticationDtoIn): AuthenticationDtoOut {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password,
            ),
        )

        val user = userDetailsService.loadUserByUsername(request.email)
        val jwt = jwtUtil.generateToken(user as UserDetailsImpl)
        return AuthenticationDtoOut(
            accessToken = jwt,
        )
    }
}
