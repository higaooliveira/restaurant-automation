package com.higor.restaurantautomation.adapters.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.higor.restaurantautomation.domain.dto.CompanyLoggedInDto
import com.higor.restaurantautomation.domain.dto.CompanyLoginDto
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager, private val jwtUtil: JWTUtil) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val credentials = jacksonObjectMapper()
                .readerFor(CompanyLoginDto::class.java)
                .readValue<CompanyLoginDto>(request.inputStream)
            val token = UsernamePasswordAuthenticationToken(credentials.email, credentials.password)
            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val id = (authResult.principal as CompanyDetails).getId().toString()
        val tokenObject = CompanyLoggedInDto(jwtUtil.generateToken(id))

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.print(MapperUtils.toJson(tokenObject))
        response.writer.flush()
    }
}
