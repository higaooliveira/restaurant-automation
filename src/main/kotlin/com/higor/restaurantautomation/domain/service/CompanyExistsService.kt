package com.higor.restaurantautomation.domain.service

interface CompanyExistsService {

    fun execute(email: String, document: String): Boolean
}
