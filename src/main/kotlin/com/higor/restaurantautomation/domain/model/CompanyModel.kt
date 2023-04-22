package com.higor.restaurantautomation.domain.model

import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.Role
import java.time.Instant
import java.util.UUID

data class CompanyModel(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    val document: String,
    val phone: String,
) {
    fun toEntity(): Company {
        return Company(
            name = name,
            email = email,
            pass = password,
            document = document,
            phone = phone,
            role = Role.ADMIN,
            createdAt = Instant.now(),
        )
    }

    companion object {
        fun fromEntity(entity: Company): CompanyModel {
            return CompanyModel(
                id = entity.id,
                name = entity.name,
                email = entity.email,
                password = entity.password,
                document = entity.document,
                phone = entity.phone,
            )
        }
    }
}
