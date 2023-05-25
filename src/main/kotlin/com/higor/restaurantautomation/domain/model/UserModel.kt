package com.higor.restaurantautomation.domain.model

import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.domain.dto.RegisterDto
import com.higor.restaurantautomation.domain.dto.UserDtoIn
import com.higor.restaurantautomation.utils.PasswordHelper
import java.time.Instant
import java.util.UUID

data class UserModel(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val role: Role,
    val createdAt: Instant,
    val companyId: UUID,
    val updatedAt: Instant? = null,
) {

    fun toEntity(company: CompanyModel): User {
        return User(
            name = name,
            email = email,
            password = password,
            role = role,
            phone = phone,
            company = company.toEntity(),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    companion object {
        fun from(other: User): UserModel {
            return UserModel(
                id = other.id,
                name = other.name,
                email = other.email,
                password = other.password,
                role = other.role,
                companyId = other.company.id!!,
                createdAt = other.createdAt,
                phone = other.phone,
                updatedAt = other.updatedAt,
            )
        }

        fun from(other: UserDtoIn): UserModel {
            return UserModel(
                name = other.name,
                email = other.email,
                password = PasswordHelper.encode(other.password),
                role = other.role,
                companyId = other.companyId,
                phone = other.phone,
                createdAt = Instant.now(),
            )
        }

        fun from(other: RegisterDto, companyId: UUID): UserModel {
            return UserModel(
                name = other.userName,
                email = other.userEmail,
                password = PasswordHelper.encode(other.password),
                role = Role.ADMIN,
                phone = other.phone,
                companyId = companyId,
                createdAt = Instant.now(),
            )
        }
    }
}
