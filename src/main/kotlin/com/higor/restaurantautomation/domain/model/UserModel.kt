package com.higor.restaurantautomation.domain.model

import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.domain.dto.RegisterDto
import com.higor.restaurantautomation.domain.dto.UserDto
import com.higor.restaurantautomation.utils.PasswordHelper
import java.time.Instant
import java.util.UUID

data class UserModel(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    val role: Role,
    val createdAt: Instant,
    val companyId: UUID,
    val updatedAt: Instant? = null,
) {

    fun toEntity(company: Company): User {
        return User(
            name = name,
            email = email,
            password = password,
            role = role,
            company = company,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    companion object {
        fun from(other: UserDto): UserModel {
            return UserModel(
                name = other.name,
                email = other.email,
                password = PasswordHelper.encode(other.password),
                role = other.role,
                companyId = other.companyId,
                createdAt = Instant.now(),
            )
        }

        fun from(other: RegisterDto, companyId: UUID): UserModel {
            return UserModel(
                name = other.userName,
                email = other.userEmail,
                password = PasswordHelper.encode(other.password),
                role = Role.ADMIN,
                companyId = companyId,
                createdAt = Instant.now(),
            )
        }
    }
}
