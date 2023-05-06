package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetUserByIdServiceImpl(
    private val repository: UserRepository,
) : GetUserByIdService {
    override fun execute(id: UUID): User {
        try {
            return repository.getReferenceById(id)
        } catch (ex: EntityNotFoundException) {
            throw ApiException(ApiErrorCodes.USER_NOT_FOUND)
        }
    }
}
