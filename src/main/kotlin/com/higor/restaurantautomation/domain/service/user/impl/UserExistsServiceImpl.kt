package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.service.user.UserExistsService
import org.springframework.stereotype.Service

@Service
class UserExistsServiceImpl(
    private val repository: UserRepository,
) : UserExistsService {
    override fun execute(email: String): Boolean {
        return repository.existsByEmail(email)
    }
}
