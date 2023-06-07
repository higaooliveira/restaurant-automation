package com.higor.restaurantautomation.utils.extensions

import com.higor.restaurantautomation.domain.dto.user.UserDtoOut
import com.higor.restaurantautomation.domain.model.user.UserModel

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
