package com.higor.restaurantautomation.domain.service.auth.impl

import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.domain.service.auth.RegisterService
import com.higor.restaurantautomation.domain.service.company.CreateCompanyService
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class RegisterServiceTest {
    private val createCompanyService: CreateCompanyService = mock {}
    private val createUserService: CreateUserService = mock {}
    private val getUserByIdService: GetUserByIdService = mock {}
    private val jwtUtil: JWTUtil = mock {}

    private val registerService: RegisterService = RegisterServiceImpl(
        createCompanyService = createCompanyService,
        createUserService = createUserService,
        getUserByIdService = getUserByIdService,
        jwtUtil = jwtUtil,
    )

    @BeforeEach
    fun setup() {
        whenever(createCompanyService.execute(any()))
            .doReturn(Factory.companyEntity.id!!)

        whenever(createUserService.execute(any()))
            .doReturn(Factory.userEntity.id!!)

        whenever(getUserByIdService.execute(any()))
            .doReturn(Factory.userEntity)

        whenever(jwtUtil.generateToken(any()))
            .doReturn("My fake JWT")
    }

    @Test
    fun `Should register process be completed`() {
        val actualJwt = registerService.execute(Factory.registerDto)
        val expectedJwt = "My fake JWT"
        assertEquals(expectedJwt, actualJwt.accessToken)
    }
}
