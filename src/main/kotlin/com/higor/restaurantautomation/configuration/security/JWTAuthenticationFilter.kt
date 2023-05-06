package com.higor.restaurantautomation.configuration.security

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.utils.extensions.toUUID
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
    private val userRepository: UserRepository,
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
        val userId = jwtUtil.getId(jwt)

        if (userId != null && SecurityContextHolder.getContext().authentication == null) {
            val user = userRepository.getReferenceById(userId.toUUID())
            val userDetails = UserDetailsImpl(user)
            if (jwtUtil.isTokenValid(jwt, user)) {
                val authToken = UsernamePasswordAuthenticationToken(user, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
                request.setAttribute("userId", user.id)
            }
        }

        chain.doFilter(request, response)
    }
}
