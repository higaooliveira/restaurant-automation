package com.higor.restaurantautomation.domain.service.user

import com.higor.restaurantautomation.domain.model.UserModel
import java.util.UUID

interface GetUserByIdService {
    fun execute(id: UUID): UserModel
}
