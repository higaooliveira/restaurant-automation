package com.higor.restaurantautomation.domain.DTO

import com.higor.restaurantautomation.domain.entity.Company

data class CompanyDTO(
        val name: String,
        val email: String,
        val password: String,
        val phone: String,
        val document: String
) {
    fun toEntity(): Company = Company(
            name = this.name,
            email = this.email,
            password = this.password,
            phone = this.phone,
            document = this.document
    )

}