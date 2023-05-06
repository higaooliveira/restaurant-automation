package com.higor.restaurantautomation.domain.service.company

interface CompanyExistsService {

    fun execute(document: String): Boolean
}
