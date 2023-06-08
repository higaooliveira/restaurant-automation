package com.higor.restaurantautomation.utils.extensions

import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.domain.dto.auth.RegisterDto
import com.higor.restaurantautomation.domain.dto.user.UserDtoIn
import com.higor.restaurantautomation.domain.dto.user.UserDtoOut
import com.higor.restaurantautomation.domain.model.company.CompanyModel
import com.higor.restaurantautomation.domain.model.user.Role
import com.higor.restaurantautomation.domain.model.user.UserModel
import com.higor.restaurantautomation.utils.PasswordHelper
import java.time.Instant
import java.util.UUID

fun UserModel.toEntity(company: CompanyModel): User {
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

fun UserModel.Companion.from(other: User): UserModel {
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

fun UserModel.Companion.from(other: UserDtoIn): UserModel {
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

fun UserModel.Companion.from(other: RegisterDto, companyId: UUID): UserModel {
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

fun UserDtoOut.Companion.from(other: UserModel): UserDtoOut {
    return UserDtoOut(
        id = other.id!!,
        name = other.name,
        email = other.email,
        role = other.role,
        phone = other.phone,
        companyId = other.companyId,
        createdAt = other.createdAt,
    )
}
