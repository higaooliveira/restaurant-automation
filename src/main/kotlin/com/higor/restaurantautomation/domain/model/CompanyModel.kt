package com.higor.restaurantautomation.domain.model

import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.domain.dto.RegisterDto
import java.time.Instant
import java.util.UUID

data class CompanyModel(
    val id: UUID? = null,
    val socialName: String,
    val fantasyName: String,
    val phone: String,
    val document: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
) {

    fun toEntity(): Company {
        return Company(
            id = id,
            socialName = socialName,
            fantasyName = fantasyName,
            phone = phone,
            document = document,
            createdAt = createdAt,
        )
    }

    companion object {

        fun from(other: Company): CompanyModel {
            return CompanyModel(
                id = other.id!!,
                socialName = other.socialName,
                fantasyName = other.fantasyName,
                phone = other.phone,
                document = other.document,
                createdAt = other.createdAt,
            )
        }

        fun from(other: RegisterDto): CompanyModel {
            return CompanyModel(
                socialName = other.socialName,
                fantasyName = other.fantasyName,
                phone = other.phone,
                document = other.document,
            )
        }
    }
}
