package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.utils.PasswordHelper

data class RegisterDto(
    val name: String,
    val email: String,
    val document: String,
    val password: String,
    val phone: String,
) {
    fun toModel(): CompanyModel {
        return CompanyModel(
            name = name,
            email = email,
            document = document,
            password = PasswordHelper.encode(password),
            phone = phone
        )
    }
}

data class AuthenticationDtoIn(
    val email: String,
    val password: String
)

data class AuthenticationDtoOut(
    val accessToken: String
)