package com.higor.restaurantautomation.controller.user

import com.higor.restaurantautomation.BaseITTest
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.dto.UserDtoIn
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest : BaseITTest() {

    private val baseEndpoint = "/api/v1/office/user"

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    private lateinit var savedUser: User

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
        companyRepository.deleteAll()
    }

    @BeforeEach
    fun setup() {
        val company = companyRepository.saveAndFlush(Factory.companyEntity)
        val user = Factory.userEntity
        user.company = company
        savedUser = userRepository.saveAndFlush(user)
    }

    @Test
    fun `GET user by id should respond 200`() {
        val token = this.generateToken(savedUser)

        sendGet(
            uri = "$baseEndpoint/${savedUser.id}",
            token = token,
        ).andExpect(status().isOk)
    }

    @Test
    fun `GET user by id should respond 404`() {
        val fakeId = "c6a749df-9663-4a00-895c-0e5da9c1b4da"

        val token = this.generateToken(savedUser)

        sendGet("$baseEndpoint/$fakeId", token)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `POST should create an user `() {
        val companyId = savedUser.company.id!!

        val token = this.generateToken(savedUser)

        val request = Factory.userDtoIn.copy(companyId = companyId)

        sendPost(baseEndpoint, request, token)
            .andExpect(status().isCreated)
    }

    @Test
    fun `POST should respond 409 when try to create an user that already exists `() {
        val companyId = savedUser.company.id!!

        val token = this.generateToken(savedUser)

        val request = UserDtoIn(
            name = savedUser.name,
            email = savedUser.email,
            password = savedUser.password,
            companyId = companyId,
            role = savedUser.role,
        )

        sendPost(baseEndpoint, request, token)
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.message").value("User already exists"))
    }

    @Test
    fun `DELETE should respond 204`() {
        val token = this.generateToken(savedUser)

        sendDelete("$baseEndpoint/${savedUser.id}", token)
            .andExpect(status().isNoContent)
    }
}
