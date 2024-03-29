package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.domain.service.user.UserExistsService
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserExistsServiceTest {
    private val userRepository: UserRepository = mock {}

    private val userExistsService: UserExistsService = UserExistsServiceImpl(userRepository)

    @BeforeEach
    fun setup() {
        whenever(userRepository.existsByEmail(Factory.userEntity.email))
            .doReturn(true)

        whenever(userRepository.existsByEmail(Factory.invalidEmail))
            .doReturn(false)
    }

    @Test
    fun `Should return true when try to validate if user exists by email`() {
        val result = userExistsService.execute(Factory.userEntity.email)

        assertEquals(true, result)
    }

    @Test
    fun `Should return false when try to validate if user exists by email`() {
        val result = userExistsService.execute(Factory.invalidEmail)
        assertEquals(false, result)
    }
}
