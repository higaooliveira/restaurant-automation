package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import com.higor.restaurantautomation.utils.factories.Factory
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GetUserByIdServiceTest {

    private val userRepository: UserRepository = Mockito.mock(UserRepository::class.java)

    private val getUserByIdService: GetUserByIdService = GetUserByIdServiceImpl(userRepository)

    private val entity = Factory.userEntity

    @BeforeEach
    fun setup() {
        Mockito
            .`when`(userRepository.getReferenceById(entity.id!!))
            .thenReturn(entity)

        Mockito
            .`when`(userRepository.getReferenceById(Factory.invalidId))
            .thenThrow(EntityNotFoundException::class.java)
    }

    @Test
    fun `Should return a valid user entity when try to get by Id`() {
        val actualEntity = getUserByIdService.execute(entity.id!!)

        assertEquals(entity.id, actualEntity.id)
        assertEquals(entity.name, actualEntity.name)
        assertEquals(entity.email, actualEntity.email)
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
