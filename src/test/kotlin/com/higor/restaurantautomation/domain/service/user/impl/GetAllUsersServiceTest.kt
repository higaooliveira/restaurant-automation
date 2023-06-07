package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.domain.dto.user.UsersFilter
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

class GetAllUsersServiceTest {
    private val userRepository: UserRepository = mock {}

    private val getAllUsersService = GetAllUsersServiceImpl(
        userRepository,
    )

    private val pageable = PageRequest.of(0, 2)

    @BeforeEach
    fun setup() {
        whenever(userRepository.findAll(any<Specification<User>>(), any<Pageable>()))
            .doReturn(
                PageImpl(mutableListOf(Factory.userEntity, Factory.user), pageable, 2L),
            )
    }

    @Test
    fun `Should filter by name`() {
        val filter = UsersFilter(name = Factory.userEntity.name)

        val result = getAllUsersService.execute(filter, pageable)

        assertEquals(result.list.first().name, Factory.userEntity.name)
        assertEquals(result.list.first().id, Factory.userEntity.id)
    }

    @Test
    fun `Should filter by email`() {
        val filter = UsersFilter(email = Factory.userEntity.email)

        val result = getAllUsersService.execute(filter, pageable)

        assertEquals(result.list.first().email, Factory.userEntity.email)
        assertEquals(result.list.first().id, Factory.userEntity.id)
    }

    @Test
    fun `Should filter by role`() {
        val filter = UsersFilter(role = Factory.userEntity.role)

        val result = getAllUsersService.execute(filter, pageable)

        assertEquals(result.list.first().role, Factory.userEntity.role)
        assertEquals(result.list.first().id, Factory.userEntity.id)
    }

    @Test
    fun `Should filter by phone`() {
        val filter = UsersFilter(phone = Factory.userEntity.phone)

        val result = getAllUsersService.execute(filter, pageable)

        assertEquals(result.list.first().phone, Factory.userEntity.phone)
        assertEquals(result.list.first().id, Factory.userEntity.id)
    }
}
