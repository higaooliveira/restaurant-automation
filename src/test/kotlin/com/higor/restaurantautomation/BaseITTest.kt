package com.higor.restaurantautomation

import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.configuration.BaseITContext
import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.configuration.security.UserDetailsImpl
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.utils.extensions.objectToJson
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

abstract class BaseITTest : BaseITContext() {

    @Autowired
    protected lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    protected lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
            .build()
    }

    protected fun <T> sendPost(uri: String, content: T, token: String): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content.objectToJson()),
        )
    }

    protected fun <T> sendPost(uri: String, content: T): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content.objectToJson()),
        )
    }

    protected fun <T> sendPut(uri: String, content: T, token: String): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content.objectToJson()),
        )
    }

    protected fun sendGet(uri: String, token: String): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON_VALUE),
        )
    }

    protected fun sendDelete(uri: String, token: String): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .delete(uri)
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON_VALUE),
        )
    }

    protected fun generateToken(user: User): String {
        val userModel = UserModel.from(user)
        return jwtUtil.generateToken(UserDetailsImpl(userModel))
    }
}
