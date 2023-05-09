package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.company.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import com.higor.restaurantautomation.domain.service.user.UserExistsService
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CreateUserServiceTest {
    private val userRepository: UserRepository = mock {}
    private val userExistsService: UserExistsService = mock {}
    private val getCompanyByIdService: GetCompanyByIdService = mock {}

    private val createUserService: CreateUserService = CreateUserServiceImpl(
        userRepository,
        userExistsService,
        getCompanyByIdService,
    )

    private val entity = Factory.userEntity
    private val inputData = UserModel.from(Factory.userEntity)

    @BeforeEach
    fun setup() {
        whenever(userExistsService.execute(Factory.invalidEmail))
            .doReturn(true)

        whenever(userExistsService.execute(entity.email))
            .doReturn(false)

        whenever(getCompanyByIdService.execute(entity.company.id!!))
            .doReturn(CompanyModel.from(Factory.companyEntity))

        whenever(userRepository.save(Mockito.any()))
            .doReturn(entity)
    }

    @Test
    fun `Should save an user properly`() {
        val actualId = createUserService.execute(inputData)

        assertEquals(entity.id, actualId)
    }

    @Test
    fun `Should throw ApiException when try to create an user with an existent email`() {
        val exception = assertThrows<ApiException> {
            createUserService.execute(Factory.existentUser)
        }
        val expectedMessage = "User already exists"

        assertEquals(expectedMessage, exception.localizedMessage)
    }
}
