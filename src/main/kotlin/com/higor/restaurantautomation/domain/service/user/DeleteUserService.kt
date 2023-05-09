package com.higor.restaurantautomation.domain.service.user

import java.util.UUID

interface DeleteUserService {

    fun execute(id: UUID)
}
