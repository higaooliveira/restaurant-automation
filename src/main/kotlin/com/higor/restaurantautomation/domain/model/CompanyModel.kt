package com.higor.restaurantautomation.domain.model

import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.domain.dto.CompanyDtoOut
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.http.HttpStatus
import java.time.Instant
import java.util.UUID

data class CompanyModel(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    val document: String,
    val phone: String,
    val role: Role = Role.ADMIN,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant?,
) {
    fun toEntity(): Company {
        return Company(
            id = id,
            name = name,
            email = email,
            pass = password,
            document = document,
            phone = phone,
            role = Role.ADMIN,
            createdAt = Instant.now(),
        )
    }

    fun toDto(): CompanyDtoOut {
        return CompanyDtoOut(
            id = id ?: throw ApiException("Company Not found", HttpStatus.NOT_FOUND),
            name = name,
            email = email,
            document = document,
            phone = phone,
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
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt,
                role = entity.role,
            )
        }
    }
}
