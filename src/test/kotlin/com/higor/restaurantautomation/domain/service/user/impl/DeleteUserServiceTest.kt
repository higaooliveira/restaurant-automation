package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.dao.EmptyResultDataAccessException

class DeleteUserServiceTest {
    private val userRepository: UserRepository = mock {}

    private val deleteUserService = DeleteUserServiceImpl(
        userRepository,
    )

    private val validId = Factory.userEntity.id!!
    private val invalidId = Factory.invalidId

    @BeforeEach
    fun setup() {
        doNothing().whenever(userRepository).deleteById(validId)
        whenever(userRepository.deleteById(invalidId)).doThrow(EmptyResultDataAccessException::class)
    }

    @Test
    fun `Should delete user by id`() {
        verify(userRepository.deleteById(validId)) {
            deleteUserService.execute(validId)
        }
    }
}
