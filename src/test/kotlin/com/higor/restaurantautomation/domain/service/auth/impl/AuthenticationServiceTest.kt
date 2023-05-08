package com.higor.restaurantautomation.domain.service.auth.impl

import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.configuration.security.UserDetailsImpl
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService

class AuthenticationServiceTest {
    private val authenticationManager: AuthenticationManager = mock {}
    private val userDetailsService: UserDetailsService = mock {}
    private val jwtUtil: JWTUtil = mock {}

    private val authentication: Authentication = mock {}

    private val authenticationService = AuthenticationServiceImpl(
        authenticationManager = authenticationManager,
        userDetailsService = userDetailsService,
        jwtUtil = jwtUtil,
    )

    @BeforeEach
    fun setup() {
        whenever(authenticationManager.authenticate(any()))
            .doReturn(authentication)

        whenever(userDetailsService.loadUserByUsername(Factory.authenticationDtoIn.email))
            .doReturn(UserDetailsImpl(Factory.userEntity))

        whenever(jwtUtil.generateToken(any()))
            .doReturn("My fake JWT")
    }

    @Test
    fun `Should return a valid jwt`() {
        val actualJwt = authenticationService.execute(Factory.authenticationDtoIn)

        assertEquals("My fake JWT", actualJwt.accessToken)
    }
}
