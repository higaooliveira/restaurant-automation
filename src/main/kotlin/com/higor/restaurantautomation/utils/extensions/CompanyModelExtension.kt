package com.higor.restaurantautomation.utils.extensions

import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.domain.dto.auth.RegisterDto
import com.higor.restaurantautomation.domain.model.company.CompanyModel

fun CompanyModel.toEntity(): Company {
    return Company(
        id = id,
        socialName = socialName,
        fantasyName = fantasyName,
        document = document,
        createdAt = createdAt,
    )
}

fun CompanyModel.Companion.from(other: Company): CompanyModel {
    return CompanyModel(
        id = other.id!!,
        socialName = other.socialName,
        fantasyName = other.fantasyName,
        document = other.document,
        createdAt = other.createdAt,
    )
}

fun CompanyModel.Companion.from(other: RegisterDto): CompanyModel {
    return CompanyModel(
        socialName = other.socialName,
        fantasyName = other.fantasyName,
        document = other.document,
    )
}
