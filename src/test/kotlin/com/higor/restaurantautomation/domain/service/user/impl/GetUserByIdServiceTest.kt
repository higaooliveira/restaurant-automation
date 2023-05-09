package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional

class GetUserByIdServiceTest {

    private val userRepository: UserRepository = mock {}

    private val getUserByIdService: GetUserByIdService = GetUserByIdServiceImpl(userRepository)

    private val model = Factory.userModel

    @BeforeEach
    fun setup() {
        whenever(userRepository.findById(Factory.userEntity.id!!))
            .doReturn(Optional.of(Factory.userEntity))

        whenever(userRepository.findById(Factory.invalidId))
            .doReturn(Optional.empty())
    }

    @Test
    fun `Should return a valid user entity when try to get by Id`() {
        val actualEntity = getUserByIdService.execute(Factory.userEntity.id!!)

        assertEquals(Factory.userEntity.id, actualEntity.id)
        assertEquals(model.name, actualEntity.name)
        assertEquals(model.email, actualEntity.email)
    }

    @Test
    fun `Should throw ApiException when try to get by id`() {
        val exception = assertThrows(ApiException::class.java) {
            getUserByIdService.execute(Factory.invalidId)
        }

        val expectedMessage = "User not found"

        assertEquals(expectedMessage, exception.localizedMessage)
    }
}
