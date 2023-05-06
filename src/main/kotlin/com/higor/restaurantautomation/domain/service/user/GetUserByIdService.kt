package com.higor.restaurantautomation.domain.service.user

import com.higor.restaurantautomation.adapters.entity.User
import java.util.UUID

interface GetUserByIdService {
    fun execute(id: UUID): User
}
