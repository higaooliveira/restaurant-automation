package com.higor.restaurantautomation.domain.service.user

import com.higor.restaurantautomation.domain.model.UserModel
import java.util.UUID

interface CreateUserService {

    fun execute(data: UserModel): UUID
}
