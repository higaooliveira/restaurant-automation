package com.higor.restaurantautomation.domain.service.user

interface UserExistsService {
    fun execute(email: String): Boolean
}
