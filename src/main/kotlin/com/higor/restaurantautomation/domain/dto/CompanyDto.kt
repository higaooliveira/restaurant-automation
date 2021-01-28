package com.higor.restaurantautomation.domain.dto


data class createCompanyDto(
        val name: String,
        val email: String,
        val password: String,
        val phone: String,
        val document: String
)