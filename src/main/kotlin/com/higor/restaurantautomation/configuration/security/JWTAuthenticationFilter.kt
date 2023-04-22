package com.higor.restaurantautomation.configuration.security

import com.higor.restaurantautomation.domain.service.GetCompanyByIdService
import com.higor.restaurantautomation.utils.toUUID
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtil,
    private val getCompanyByIdService: GetCompanyByIdService,
) : OncePerRequestFilter() {

    private val authorization = "Authorization"
    private val bearer = "Bearer"

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authHeader = request.getHeader(authorization)

        if (authHeader == null || !authHeader.startsWith(bearer)) {
            chain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)
        val companyId = jwtUtil.getId(jwt)

        if (companyId != null && SecurityContextHolder.getContext().authentication == null) {
            val company = this.getCompanyByIdService.execute(companyId.toUUID()).toEntity()

            if (jwtUtil.isTokenValid(jwt, company)) {
                val authToken = UsernamePasswordAuthenticationToken(company, null, company.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
                request.setAttribute("companyId", company.id)
            }
        }

        chain.doFilter(request, response)
    }
}
