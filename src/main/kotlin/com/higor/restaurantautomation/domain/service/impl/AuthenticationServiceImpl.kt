package com.higor.restaurantautomation.domain.service.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.service.AuthenticationService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val companyRepository: CompanyRepository,
    private val jwtUtil: JWTUtil,
) : AuthenticationService {
    override fun execute(request: AuthenticationDtoIn): AuthenticationDtoOut {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password,
            ),
        )

        val company = companyRepository.findByEmail(request.email) ?: throw ApiException("Invalid Credentials", statusCode = HttpStatus.UNAUTHORIZED)
        val jwt = jwtUtil.generateToken(company)
        return AuthenticationDtoOut(
            accessToken = jwt,
        )
    }
}
