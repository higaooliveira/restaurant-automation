package com.higor.restaurantautomation.controller.auth

import com.higor.restaurantautomation.BaseITTest
import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoIn
import com.higor.restaurantautomation.utils.PasswordHelper
import com.higor.restaurantautomation.utils.factories.Factory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class AuthenticationControllerTest : BaseITTest() {

    private val baseEndpoint = "/api/v1/office/auth"

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    private lateinit var savedCompany: Company
    private lateinit var savedUser: User
    private val password = Factory.userEntity.password

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
        companyRepository.deleteAll()
    }

    @BeforeEach
    fun setup() {
        savedCompany = companyRepository.saveAndFlush(Factory.companyEntity)
        val user = Factory.userEntity
        user.company = savedCompany
        user.password = PasswordHelper.encode(password)
        savedUser = userRepository.saveAndFlush(user)
    }

    @Test
    fun `POST register should create a company and an user`() {
        val request = Factory.registerDtoIT

        val token = generateToken(savedUser)

        sendPost("$baseEndpoint/register", request, token = token)
            .andExpect(status().isCreated)
    }

    @Test
    fun `POST register should respond 409 when try to create a company that already exist`() {
        val request = Factory.registerDtoIT.copy(document = savedCompany.document)
        val token = generateToken(savedUser)

        sendPost("$baseEndpoint/register", request, token = token)
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.message").value("Company already exists"))
    }

    @Test
    fun `POST register should respond 409 when try to create an user that already exists`() {
        val request = Factory.registerDtoIT.copy(userEmail = savedUser.email)
        val token = generateToken(savedUser)

        sendPost("$baseEndpoint/register", request, token = token)
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.message").value("User already exists"))
    }

    @Test
    @Transactional
    fun `POST authenticate should respond 200`() {
        val request = AuthenticationDtoIn(
            email = savedUser.email,
            password = password,
        )

        sendPost("$baseEndpoint/authenticate", request)
            .andExpect(status().isOk)
    }

    @Test
    fun `POST authenticate should respond 403`() {
        val request = AuthenticationDtoIn(
            email = savedUser.email,
            password = savedUser.password,
        )

        sendPost("$baseEndpoint/authenticate", request)
            .andExpect(status().isForbidden)
    }
}
